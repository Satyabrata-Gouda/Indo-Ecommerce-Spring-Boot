package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser,Long> {

    public Optional<AppUser> findByEmail(String email);
}
