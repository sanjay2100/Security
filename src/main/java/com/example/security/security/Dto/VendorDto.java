package com.example.security.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorDto {
    private String id;
    private String vendorname;
    private String email;
    private String mobile;
    private String address;
    private String username;
    private String password;
    private String state;
    private String district;
    private String pincode;
    private String role;
}
