package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.config.JwtProvider;
import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.repo.UserRepo;
import com.ecommerce.indo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AppUser fetchUserById(Long userId) {

        try{
            AppUser user = userRepo.findById(userId).orElseThrow(()->new AuthException("User not found"));
            return user;
        }catch (Exception e){
            throw new AuthException(e.getMessage());

        }
    }

    @Override
    public AppUser fetchUserProfileByToken(String jwtToken) {
        try {
            String email = jwtProvider.emailFromJwtExtractor(jwtToken);
            return userRepo.findByEmail(email).orElseThrow(() -> new AuthException("User not found"));
        }catch (Exception e){
            throw new AuthException(e.getMessage());

        }
    }
}
