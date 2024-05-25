package com.ecommerce.indo.config;

import com.ecommerce.indo.UserRepo;
import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.AppUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepo.findAppUserByEmail(username);

        if(ObjectUtils.isEmpty(user)){
          throw new AuthException("User not found with given mail -"+username);
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();


        return new User(user.getEmail(),user.getPassword(),authorityList);
    }
}
