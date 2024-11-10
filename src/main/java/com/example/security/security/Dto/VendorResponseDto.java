package com.example.security.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorResponseDto {
    private String email;
    private String mobile;
    private String address;
    private String username;
}