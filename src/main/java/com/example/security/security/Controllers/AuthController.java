package com.example.security.security.Controllers;

import com.example.security.security.Dto.LoginDto;
import com.example.security.security.Dto.SuccessDto;
import com.example.security.security.Dto.UserDto;
import com.example.security.security.Service.AuthService;
import com.example.security.security.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private JwtService jwtService;
    @GetMapping("/")
    public String HelloWorld(){
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessDto> createUser(@RequestBody UserDto dto){
        try{
            UserDto saved=service.registerUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDto("User created successfully"));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SuccessDto(e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserDto dto){
        try {
            String token=service.loginUser(dto);
            return new ResponseEntity<>(new LoginDto(token,"Logged in succesfully"),HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/validate")
        public ResponseEntity<Object> validateToken(@RequestParam String token){
            try {
              boolean res=  jwtService.ValidateToken(token);
              return new ResponseEntity<>(res,HttpStatus.OK);
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
            }
        }

}
