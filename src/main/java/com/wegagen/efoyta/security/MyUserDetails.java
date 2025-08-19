package com.wegagen.efoyta.security;

import com.wegagen.efoyta.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {
    private final Map<String, Object> user;
    private String salt;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.get("hash").toString();
    }

    @Override
    public String getUsername() {
        return user.get("Username").toString();
    }
}
