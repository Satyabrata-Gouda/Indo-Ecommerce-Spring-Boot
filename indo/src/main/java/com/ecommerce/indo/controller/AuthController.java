package com.ecommerce.indo.controller;

import com.ecommerce.indo.UserRepo;
import com.ecommerce.indo.config.CustomUserDetails;
import com.ecommerce.indo.config.JwtProvider;
import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.AuthResponse;
import com.ecommerce.indo.model.SignInRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetails customUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody AppUser user){

        String email =  user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        AppUser appUser = userRepo.findAppUserByEmail(email);

        if(ObjectUtils.isNotEmpty(appUser)){
            throw new AuthException("Email is already used with another account.");
        }

        AppUser createdUser = new AppUser();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        AppUser savedUser = userRepo.save(createdUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtProvider.generateToken(auth);

        return ResponseEntity.ok(AuthResponse.builder().token(token).message("Sign-up successfully.").build());

    }

@PostMapping("/signin")
    public ResponseEntity<AuthResponse> signInUser(@RequestBody SignInRequest signInRequest){

        String userName = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        Authentication authentication = authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(AuthResponse.builder().token(token).message("Sign-in successfully.").build());
    }

    private Authentication authenticate(String userName, String password){
        UserDetails userDetails =   customUserDetails.loadUserByUsername(userName);

        if(ObjectUtils.isEmpty(userDetails)){
            throw new AuthException("Invalid user-name.");
        }
        if(passwordEncoder.matches(password,userDetails.getPassword())){
            throw new AuthException("Invalid password.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
