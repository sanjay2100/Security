package com.example.security.security.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretkey;
    public String generateToken(String username){
        HashMap<String, Objects>claims=new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+(60*60*60*1000*2)))
                .and()
                .signWith(SignatureAlgorithm.HS256,secretkey)
                .compact();
    }

    private Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretkey));
    }

    public boolean ValidateToken(String token) throws Exception{
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) getKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }

    }
}
