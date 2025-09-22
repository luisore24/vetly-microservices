package com.company.microservice_auth.ServiceImpl.user;

import com.company.microservice_auth.dto.user.UserRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.mapper.UserMapper;
import com.company.microservice_auth.repository.user.UserRepository;
import com.company.microservice_auth.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponseDTO register(UserRequestDTO request) {
        User user = UserMapper.instance.userRequestDTOToUser(request);

        User userRegistered = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(userRegistered.getId())
                .username(userRegistered.getUsername())
                .name(userRegistered.getName())
                .lastname(userRegistered.getLastname())
                .roles(userRegistered.getRoles())
                .email(userRegistered.getEmail())
                .phone(userRegistered.getPhone())
                .address(userRegistered.getAddress())
                .status(userRegistered.getStatus())
                .build();
    }
}
