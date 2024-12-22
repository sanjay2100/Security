package com.example.security.security.Modals;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="userdetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @NonNull
    private String name;
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

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
