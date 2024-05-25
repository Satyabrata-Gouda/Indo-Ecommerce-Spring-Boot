package com.ecommerce.indo;

import com.ecommerce.indo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser,Long> {

    public AppUser findAppUserByEmail(String email);
}
