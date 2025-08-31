package com.company.microservice_auth.service.ServiceImpl;


import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFound = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: "+username+" not exist!. Try with an user valid."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userFound.getRoles().forEach(
                userRole -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userRole.getRole().getDescription()))));

        userFound.getRoles().stream()
                .flatMap(userRole -> userRole.getRole().getRolePermissions().stream())
                .forEach(rolePermission -> authorityList.add(new SimpleGrantedAuthority(rolePermission.getPermission().getDescription())));

        return new org.springframework.security.core.userdetails.User(
                userFound.getUsername(),
                userFound.getPassword(),
                userFound.isEnabled(),
                userFound.isAccountNoExpired(),
                userFound.isCredentialNoExpired(),
                userFound.isAccountNoLocked(),
                authorityList);
    }
}
