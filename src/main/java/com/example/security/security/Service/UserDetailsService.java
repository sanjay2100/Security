package com.example.security.security.Service;

import com.example.security.security.Dto.UserDetailsDto;
import com.example.security.security.Dto.ValidTokenDto;
import com.example.security.security.Exceptions.Exceptions.CustomException;
import com.example.security.security.Mapper.UserDetailsMapper;
import com.example.security.security.Modals.User;
import com.example.security.security.Modals.UserDetails;
import com.example.security.security.Repository.UserDetailsRepo;
import com.example.security.security.Repository.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public String createUserDetails(UserDetailsDto userDetailsdto, String token){
        try {
            String sub=token.substring(7);
            ValidTokenDto isValid=jwtService.ValidateToken(sub);
            if(isValid.isValid()==true){
                userDetailsdto.setUserId(isValid.getUsername());
                UserDetails userDetails= UserDetailsMapper.mapDtoToModal(userDetailsdto);
                UserDetails saved=userDetailsRepo.save(userDetails);
                return "User details added successfully";
            }
            else{
                throw new AuthenticationException("Invalid token");
            }

        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public UserDetailsDto getUserDetails(String token){
        try{
            String sub=token.substring(7);
            ValidTokenDto isValid=jwtService.ValidateToken(sub);
            if(isValid.isValid()){
                UserDetails userDetails=userDetailsRepo.getByUserId(isValid.getUsername());

                if(userDetails!=null){
                    UserDetailsDto userDetailsDto=UserDetailsMapper.mapModalToDto(userDetails);
                    return userDetailsDto;
                }
                else{
                    throw new CustomException("User details not found");
                }
            }
            else{
                throw new AuthenticationException("Token is invalid");
            }
        }

        catch (NullPointerException e){
            throw new CustomException(e.getMessage());
        }
        catch(Exception e){
            throw new CustomException(e.getMessage());
        }

    }


}
