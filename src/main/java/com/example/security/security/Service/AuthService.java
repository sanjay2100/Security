package com.example.security.security.Service;

import com.example.security.security.Dto.UserDto;
import com.example.security.security.Dto.VendorDto;
import com.example.security.security.Dto.VendorResponseDto;
import com.example.security.security.Mapper.UserMapper;
import com.example.security.security.Mapper.VendorMapper;
import com.example.security.security.Modals.User;
import com.example.security.security.Modals.Vendor;
import com.example.security.security.Repository.UserRepository;
import com.example.security.security.Repository.VendorRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private VendorRepository vendorRepository;
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

    public VendorResponseDto registerVendor(VendorDto dto,String token) throws Exception {
        String username;
        String bearerremoved=token.substring(7);
        if(bearerremoved!=null){
           username= redisService.getUserNameFromToken(bearerremoved);
        }
        else{
            username= dto.getUsername();
        }

        User exist=repository.findByUsername(username);
        if(exist==null|| Objects.equals(exist.getRole(), "user")){
            if(exist==null){
                dto.setRole("vendor");
                Vendor vendor= VendorMapper.mapDtoToVendor(dto);
                User userDetails=new User(dto.getId(),dto.getUsername(),dto.getPassword(),"vendor");
                userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                User saved=repository.save(userDetails);
                if(saved!=null){
                    Vendor savedVendor=vendorRepository.save(vendor);
                    VendorResponseDto vendorDto=VendorMapper.mapVendorToDto(savedVendor);
                    return vendorDto;
                }
            }
            else{
                Vendor vendor= VendorMapper.mapDtoToVendor(dto);
                exist.setRole("vendor");
                vendor.setUserid(username);
                User saved=repository.save(exist);
                Vendor mobileNumber=vendorRepository.findByMobile(vendor.getMobile());
                if(mobileNumber!=null){
                    if(saved!=null){
                        Vendor savedVendor=vendorRepository.save(vendor);
                        VendorResponseDto vendorDto=VendorMapper.mapVendorToDto(savedVendor);
                        return vendorDto;
                    }
                }
                else{
                    throw new AuthenticationException("Mobile number alreday exist");
                }

            }

        }

        throw new Exception("User already a vendor");


    }
    public Map loginUser(UserDto dto) throws Exception{
        User user=UserMapper.mapDtoToUser(dto);
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(auth.isAuthenticated()){
            String token=jwtService.generateToken(dto.getUsername());
            boolean redis=redisService.storeRedisToken(token, dto.getUsername());
            User userDetails=repository.findByUsername(dto.getUsername());
            if (redis){
                Map<String,String> details =new HashMap<>();
                details.put("token",token);
                details.put("role", userDetails.getRole());
                return details;
            }
        }

        throw new AuthenticationException("Invalid credentials");
    }


}
