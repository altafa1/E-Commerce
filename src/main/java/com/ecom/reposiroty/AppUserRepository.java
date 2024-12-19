package com.ecom.reposiroty;

import com.ecom.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByEmailOrPhone(String email, String phone);
    Optional<AppUser>findByName(String name);

}