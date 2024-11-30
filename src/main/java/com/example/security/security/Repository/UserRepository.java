package com.example.security.security.Repository;

import com.example.security.security.Modals.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByUsername(String username);

}
