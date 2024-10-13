package com.example.security.security.Controllers;


import com.example.security.security.Dto.UserDto;
import com.example.security.security.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/vendor")
public class VendorController {

    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<Object> createVendor(@RequestBody UserDto dto){
        try{
            UserDto saved=authService.registerVendor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vendor registered successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }
}
