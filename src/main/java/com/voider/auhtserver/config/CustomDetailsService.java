package com.voider.auhtserver.config;

import com.voider.auhtserver.dao.user.UserRepository;
import com.voider.auhtserver.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    UserRepository oauthDao;

    @Override
    public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
        User userEntity = null;
        try {
            userEntity = oauthDao.findByEmail(username);
            CustomUser customUser = new CustomUser(userEntity);
            return customUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}