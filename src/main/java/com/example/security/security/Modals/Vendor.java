package com.example.security.security.Modals;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userid;
    @NonNull
    private String email;
    @NonNull
    private String mobile;
    @NonNull
    private String address;
    @NonNull
    private String state;
    @NonNull
    private String district;
    @NonNull
    private String pincode;
}
