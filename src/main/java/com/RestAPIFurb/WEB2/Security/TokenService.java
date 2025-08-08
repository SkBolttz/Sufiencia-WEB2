package com.RestAPIFurb.WEB2.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.RestAPIFurb.WEB2.Entity.Funcionario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private long expirationMillis;

    public String gerarToken(Funcionario funcionario) {
        return JWT.create()
                .withIssuer("RestAPIFurb")
                .withSubject(funcionario.getCpf())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMillis))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validarToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("RestAPIFurb")
                .build()
                .verify(token)
                .getSubject();
    }
}