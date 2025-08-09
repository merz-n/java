package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.UserDao;
import org.example.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.error("Username we search: " + username);

        var user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        logger.error(user.toString());

        var userDetails =  User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .disabled(!user.getEnabled())
                .authorities(user.getAuthorities().stream()
                        .map(Authority::getAuthority)
                        .toArray(String[]::new))
                .build();

        logger.error(userDetails.toString());
        return userDetails;
    }
}

