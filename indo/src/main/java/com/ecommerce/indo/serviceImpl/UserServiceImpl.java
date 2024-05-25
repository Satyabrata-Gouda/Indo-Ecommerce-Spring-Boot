package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public AppUser fetchUserById(Long userId) {
        return null;
    }

    @Override
    public AppUser fetchUserProfileByToken(String jwtToken) {
        return null;
    }
}
