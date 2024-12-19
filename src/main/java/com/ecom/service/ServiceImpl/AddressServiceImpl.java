package com.ecom.service.ServiceImpl;

import com.ecom.entity.Address;
import com.ecom.entity.AppUser;
import com.ecom.reposiroty.AddressRepository;
import com.ecom.reposiroty.AppUserRepository;
import com.ecom.service.AddressService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AppUserRepository appUserRepository;

    public AddressServiceImpl(AddressRepository addressRepository, AppUserRepository appUserRepository) {
        this.addressRepository = addressRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Address addAddress(Address address, AppUser user) {
        if (user != null) {
            address.setAppUser(user);
            String addressId = UUID.randomUUID().toString();
            address.setId(addressId);
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public void deleteAddress( String addId) {
        addressRepository.findById(addId).orElseThrow(
                ()->new RuntimeException(" address not found")
        );

        addressRepository.deleteById(addId);

    }
}
