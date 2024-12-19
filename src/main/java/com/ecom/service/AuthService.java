package com.ecom.service;

import com.ecom.payload.AppUserDto;
import com.ecom.payload.LoginDto;

public interface AuthService {
    AppUserDto registerUser(AppUserDto dto);

    String loginUser(LoginDto dto);
}
