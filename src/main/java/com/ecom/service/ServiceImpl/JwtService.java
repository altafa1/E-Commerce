package com.ecom.service.ServiceImpl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ecom.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.algorithm.issuer}")
    private String issuer;
    @Value("${jwt.expiry.time}")
    private int expiryTime;


    private Algorithm algorithm;

    String UserName="username";

    @PostConstruct
    public void PostConstruct() throws Exception{
        algorithm=Algorithm.HMAC256(algorithmKey);

    }

    public String generateToken(AppUser appUser){
        return JWT.create()
                .withClaim(UserName,appUser.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getClaim(String token){
        DecodedJWT decodedJWT= JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJWT.getClaim(UserName).asString();
    }
}
