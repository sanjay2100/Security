package com.example.security.security.Repository;

import com.example.security.security.Modals.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<UserDetails,Long> {

    public UserDetails getByUserId(String userid);
}
