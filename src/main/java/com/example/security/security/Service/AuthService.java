package com.example.security.security.Service;

import com.example.security.security.Dto.UserDto;
import com.example.security.security.Mapper.UserMapper;
import com.example.security.security.Modals.User;
import com.example.security.security.Repository.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisService redisService;

    public UserDto registerUser(UserDto dto) throws Exception {
        User exist=repository.findByUsername(dto.getUsername());
        if(exist==null){
            dto.setRole("user");
            User user= UserMapper.mapDtoToUser(dto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User saved=repository.save(user);
            UserDto savedDto=UserMapper.mapUserToDto(saved);
            return savedDto;
        }

        throw new Exception("User already exist");


    }

    public UserDto registerVendor(UserDto dto) throws Exception {
        User exist=repository.findByUsername(dto.getUsername());
        if(exist==null|| Objects.equals(exist.getRole(), "user")){
            if(exist==null){
                dto.setRole("vendor");
                User user= UserMapper.mapDtoToUser(dto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User saved=repository.save(user);
                return UserMapper.mapUserToDto(saved);
            }
            else{
                exist.setRole("vendor");
                User saved=repository.save(exist);
                return UserMapper.mapUserToDto(saved);
            }

        }

        throw new Exception("User already exist");


    }

    public String loginUser(UserDto dto) throws Exception{
        User user=UserMapper.mapDtoToUser(dto);
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(auth.isAuthenticated()){
            String token=jwtService.generateToken(dto.getUsername());
            boolean redis=redisService.storeRedisToken(token, dto.getUsername());
            if (redis){
                return token;
            }
        }

        throw new AuthenticationException("Invalid credentials");
    }
}
