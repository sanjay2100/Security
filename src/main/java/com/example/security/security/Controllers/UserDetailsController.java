package com.example.security.security.Controllers;


import com.example.security.security.Dto.SuccessDto;
import com.example.security.security.Dto.UserDetailsDto;
import com.example.security.security.Dto.UserdetailStatusDto;
import com.example.security.security.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/check_user_details")
    public ResponseEntity<UserdetailStatusDto> checkUserDetailStatus(@RequestHeader (value = "Authorization") String token){
        boolean availability= userDetailsService.checkUserDetailAvailable(token);
        return new ResponseEntity<>(new UserdetailStatusDto(availability),HttpStatus.OK);
    }

    @PostMapping("post_user_details")
    public ResponseEntity<SuccessDto> createUserDetails(@RequestHeader(value = "Authorization")String token,@RequestBody UserDetailsDto detailsDto){
        String response= userDetailsService.createUserDetails(detailsDto,token);
        return new ResponseEntity<>(new SuccessDto(response),HttpStatus.CREATED);
    }

    @PostMapping("/get_user_details")
    public ResponseEntity<UserDetailsDto> getUserDetails(@RequestHeader (value = "Authorization") String token){
        UserDetailsDto userDetailsDto=userDetailsService.getUserDetails(token);
        return new ResponseEntity<>(userDetailsDto,HttpStatus.OK);
    }
}
