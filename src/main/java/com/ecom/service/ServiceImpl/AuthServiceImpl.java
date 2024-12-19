package com.ecom.service.ServiceImpl;

import com.ecom.configuration.ConfigClass;
import com.ecom.customExceptions.UserAlreadyExistException;
import com.ecom.entity.AppUser;
import com.ecom.payload.AppUserDto;
import com.ecom.payload.LoginDto;
import com.ecom.reposiroty.AppUserRepository;
import com.ecom.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private ModelMapper modelMapper;
    private AppUserRepository appUserRepository;
private ConfigClass configClass;
private JwtService jwtService;
    public AuthServiceImpl(ModelMapper modelMapper, AppUserRepository appUserRepository, ConfigClass configClass, JwtService jwtService) {
        this.modelMapper = modelMapper;
        this.appUserRepository = appUserRepository;
        this.configClass = configClass;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto registerUser(AppUserDto dto) {

        Optional<AppUser> byEmailOrPhone = appUserRepository.findByEmailOrPhone(dto.getEmail(), dto.getPhone());
    if(byEmailOrPhone.isPresent()) {
        throw new UserAlreadyExistException("user already exist with this email or username");
    }

        String userId = UUID.randomUUID().toString();
        dto.setId(userId);
        String encodedPass = configClass.passwordEncoder().encode(dto.getPassword());
        dto.setPassword(encodedPass);
        AppUser appUser = mapToEntity(dto);

        AppUser save = appUserRepository.save(appUser);
        return mapToDto(save);
    }

    @Override
    public String loginUser(LoginDto dto) {
        Optional<AppUser> byEmailOrPhone = appUserRepository.findByEmailOrPhone(dto.getUserName(), dto.getUserName());
        if (byEmailOrPhone.isPresent()){
            AppUser appUser = byEmailOrPhone.get();
            boolean matches = configClass.passwordEncoder().matches(dto.getPassword(), appUser.getPassword());
            if(matches){
                String token = jwtService.generateToken(appUser);
                return token;
            }
        }
        return null;
    }

    AppUser mapToEntity(AppUserDto dto){
       return  modelMapper.map(dto,AppUser.class);
    }

    AppUserDto mapToDto(AppUser appUser){
        return modelMapper.map(appUser,AppUserDto.class);
    }

}
