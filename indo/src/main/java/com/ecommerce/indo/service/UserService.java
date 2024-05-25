package com.ecommerce.indo.service;

import com.ecommerce.indo.model.AppUser;

public interface UserService {

    public AppUser fetchUserById(Long userId);

    public AppUser fetchUserProfileByToken(String jwtToken);


}
