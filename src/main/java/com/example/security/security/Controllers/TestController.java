package com.example.security.security.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Test")
public class TestController {
    @GetMapping("/")
    public String protectedRoute(){
        return "Proytected";
    }
}
