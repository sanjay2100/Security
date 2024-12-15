package com.example.security.security.Modals;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @NonNull
    private String address;
    @NonNull
    private String district;
    @NonNull
    private String state;
    @NonNull
    private String pincode;
    @NonNull
    private String mobilenumber;
    @NonNull
    private String email;
}
