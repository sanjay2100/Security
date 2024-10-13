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
    private String email;
    private String mobile;
    private String address;
}
