package com.ecom.controller;

import com.ecom.payload.AppUserDto;
import com.ecom.payload.LoginDto;
import com.ecom.payload.TokenHolder;
import com.ecom.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registeruser")
        public ResponseEntity<AppUserDto> registerUser(@RequestBody AppUserDto dto){
        AppUserDto registeredUser=authService.registerUser(dto);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        }

        @PostMapping("/login")
    public ResponseEntity<?>loginUser(@RequestBody LoginDto dto) {
            String token = authService.loginUser(dto);
            if (token != null) {
                TokenHolder th = new TokenHolder();
                th.setToken(token);
                th.setTokenType("jwt");
                return new ResponseEntity<>(th, HttpStatus.OK);
            }
            return new ResponseEntity<>("credentials were wrong", HttpStatus.EXPECTATION_FAILED);

        }
}
