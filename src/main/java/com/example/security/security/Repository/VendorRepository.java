package com.example.security.security.Repository;

import com.example.security.security.Modals.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface VendorRepository extends JpaRepository<Vendor,String> {
    public Vendor findByUserid(String id);
    public Vendor findByMobile(String mobile);
}
