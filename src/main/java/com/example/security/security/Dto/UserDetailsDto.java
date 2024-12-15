package com.example.security.security.Dto;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetailsDto {
    @Id
    private Long id;
    private String userId;
    private String address;
    private String district;
    private String state;
    private String pincode;
    private String mobilenumber;
    private String email;
}
