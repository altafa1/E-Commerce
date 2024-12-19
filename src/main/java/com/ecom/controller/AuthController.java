package com.ecom.controller;

import com.ecom.payload.AppUserDto;
import com.ecom.payload.LoginDto;
import com.ecom.service.AuthService;
import org.hibernate.sql.results.internal.ResolvedSqlSelection;
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
    public ResponseEntity<String>loginUser(@RequestBody LoginDto dto){
        boolean res=authService.loginUser(dto);
        if(res){
            return new ResponseEntity<>("login successfully",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("credentials were wrong",HttpStatus.BAD_REQUEST);

        }

}
