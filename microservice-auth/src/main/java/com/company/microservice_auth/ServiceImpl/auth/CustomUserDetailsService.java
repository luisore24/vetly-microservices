package com.company.microservice_auth.ServiceImpl.auth;


import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.repository.auth.AuthRepository;
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

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User userFound = userRepository.findByUsernameWithRolesPermissionsMenus(username)
        User userFound = authRepository.findByUsernameWithRolesPermissions(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User: "+username+" not exist!. Try with an user valid."));

            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

            userFound.getRoles().forEach(
                    userRole -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userRole.getRole().getDescription()))));

            userFound.getRoles().stream()
                    .flatMap(userRole -> userRole.getRole().getRolePermissions().stream())
                    .forEach(rolePermission -> authorityList.add(new SimpleGrantedAuthority(rolePermission.getPermission().getName())));

            return new org.springframework.security.core.userdetails.User(
                    userFound.getUsername(),
                    userFound.getPassword(),
                    userFound.getIsEnabled(),
                    userFound.getAccountNoExpired(),
                    userFound.getCredentialNoExpired(),
                    userFound.getAccountNoLocked(),
                    authorityList);

    }
}
