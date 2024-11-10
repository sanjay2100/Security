package com.example.security.security.Controllers;


import com.example.security.security.Dto.SuccessDto;
import com.example.security.security.Dto.UserDto;
import com.example.security.security.Dto.VendorDto;
import com.example.security.security.Dto.VendorResponseDto;
import com.example.security.security.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/vendor")
public class VendorController {

    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<Object> createVendor(@RequestBody VendorDto dto,@RequestHeader(value = "Authorization") String token){
        try{
            VendorResponseDto saved=authService.registerVendor(dto,token);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDto("Vendor registered successfully"));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SuccessDto(e.getMessage()));
        }

    }
}
