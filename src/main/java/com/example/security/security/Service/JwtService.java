package com.example.security.security.Service;

import com.example.security.security.Dto.ValidTokenDto;
import com.example.security.security.Modals.User;
import com.example.security.security.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class JwtService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RedisService redisService;
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

    public ValidTokenDto ValidateToken(String token) throws Exception{
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) getKey())
                    .build()
                    .parseSignedClaims(token);
            String username=redisService.getUserNameFromToken(token);
            User userdetails=repository.findByUsername(username);

            return new ValidTokenDto(true,userdetails.getId());
        }
        catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }

    }
}
