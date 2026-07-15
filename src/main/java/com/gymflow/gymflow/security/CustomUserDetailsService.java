package com.gymflow.gymflow.security;

import com.gymflow.gymflow.entity.User;

import com.gymflow.gymflow.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User Usname= (userRepo.findByusername(username).orElseThrow(()-> new UsernameNotFoundException("not found ")));

      return  new CustomUserDetails(Usname);

    }
}
