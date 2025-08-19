package com.wegagen.efoyta.security;

import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.repository.MssqlViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MssqlViewRepository mssqlViewRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Map<String, Object> user = mssqlViewRepository.findActiveUserByUsername(userName)
                .orElseThrow(()-> new UsernameNotFoundException("No user found with this user name: " + userName));
        return new MyUserDetails(user);
    }
}
