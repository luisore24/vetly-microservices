package com.company.microservice_auth.ServiceImpl.user;

import com.company.microservice_auth.aspect.Observed;
import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.user.UserCreateRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.dto.user.UserUpdateRequestDTO;
import com.company.microservice_auth.entity.Role;
import com.company.microservice_auth.entity.Status;
import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.entity.UserRole;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.exception.common.ResourceNullException;
import com.company.microservice_auth.mapper.UserMapper;
import com.company.microservice_auth.repository.user.UserRepository;
import com.company.microservice_auth.service.role.RoleInternalService;
import com.company.microservice_auth.service.status.StatusInternalService;
import com.company.microservice_auth.service.user.UserInternalDTOService;
import com.company.microservice_auth.service.user.UserInternalService;
import com.company.microservice_auth.service.user.UserService;
import com.company.microservice_auth.util.AuditHelper;
import com.company.microservice_auth.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserInternalService, UserInternalDTOService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuditHelper auditHelper;
    private final MessageHelper messageHelper;
    private final StatusInternalService statusInternalService;
    private final RoleInternalService roleInternalService;
    private final UserMapper userMapper;


    @Override
    @Transactional
    @Observed(event = "USER_REGISTER", logRequest = false)
    public ApiResponse<UserResponseDTO> register(UserCreateRequestDTO request) {

        log.info("Begin process user register");
        log.info("Find if user exists: {}", request.getUsername());
        Optional<User> userFound = userRepository.findByUsername(request.getUsername());

        if(userFound.isPresent()) {
            log.warn("user already exist");
            throw new ResourceAlreadyExistException("USER ALREADY EXIST");
        }

        log.info("User available");

        User user = userMapper.userCreateRequestDTOToUser(request);

        String message = messageHelper.getMessage("process.successful.message");

        Status status = statusInternalService.findEntityById(request.getStatusDTO().getId());

        user.setStatus(status);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setIsEnabled(status.getIsAllowed());
        user.setAccountNoExpired(true);
        user.setAccountNoLocked(status.getIsAllowed());
        user.setCredentialNoExpired(true);
        user.setCreatedBy(auditHelper.getUserLogged());
        user.setCreatedAt(auditHelper.getTimeNow());
        user.setUpdatedBy(auditHelper.getUserLogged());
        user.setUpdatedAt(auditHelper.getTimeNow());
        user.setIsDeleted(false);

        if(request.getRolesDTO() != null && !request.getRolesDTO().isEmpty()){
            Set<Role> roles = request.getRolesDTO().stream()
                    .map(roleInternalService::findEntityById)
                    .collect(Collectors.toSet());

            Set<UserRole> userRoles = roles.stream()
                            .map(role -> {
                                UserRole userRole = new UserRole();
                                userRole.setUser(user);
                                userRole.setRole(role);
                                userRole.setStatus(status);
                                userRole.setCreatedBy(auditHelper.getUserLogged());
                                userRole.setCreatedAt(auditHelper.getTimeNow());
                                userRole.setUpdatedAt(auditHelper.getTimeNow());
                                userRole.setUpdatedBy(auditHelper.getUserLogged());
                                return  userRole;
                            })
                                    .collect(Collectors.toSet());


            user.setRoles(userRoles);

        }

        User userRegistered = userRepository.save(user);

        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(userRegistered);

        log.info("End proccess user register: Successfull");

        return new ApiResponse<>(true, message, userResponseDTO);
    }



    @Override
    @Transactional
    @Observed(event = "USER_UPDATE")
    public ApiResponse<UserResponseDTO> update(UserUpdateRequestDTO request) {

        log.info("Begin update user");

        Optional<User> userFound = userRepository.findById(request.getId());

        String message = messageHelper.getMessage("process.successful.message");

        if(userFound.isEmpty()){
            throw new ResourceNotFoundException("USER NOT FOUND");
        }

        Status status = statusInternalService.findEntityById(request.getStatus().getId());

        User user = userFound.get();

        if(request.getRolesDTO() == null){
            throw new ResourceNullException("ROLES IS NULL");
        }

        //Get Roles from request and then valid if exist with roleInternalService -> return a set of RoleEntity
        Set<Role> requestRoles = request.getRolesDTO().stream()
                        .map(userRoleDTO -> roleInternalService.findEntityById(userRoleDTO.getRoleDTO().getId()))
                                .collect(Collectors.toSet());

        //Get UserRole current user in DB
        Set<UserRole> userRoles = user.getRoles();

        //Get Role current in UserRole from DB
        Set<Role> currentRoles = userRoles.stream()
                .map(UserRole::getRole).collect(Collectors.toSet());


        //valid userRoles nor exist in request. If no exist remove
        Set<UserRole> removeRoles = userRoles.stream()
                        .filter(userRole -> !requestRoles.contains(userRole.getRole())).collect(Collectors.toSet());


        //remove userRoles no exist in set from current user
        userRoles.removeAll(removeRoles);


        //valid new roles in request. If exists, add a current Set from DB and save after.
        Set<Role> addRoles = requestRoles.stream()
                        .filter(requestRole -> !currentRoles.contains(requestRole)).collect(Collectors.toSet());

        for (Role newRole : addRoles){
            UserRole newUserRoles = new UserRole();
            newUserRoles.setUser(user);
            newUserRoles.setRole(newRole);
            newUserRoles.setCreatedBy(auditHelper.getUserLogged());
            newUserRoles.setCreatedAt(auditHelper.getTimeNow());
            newUserRoles.setUpdatedBy(auditHelper.getUserLogged());
            newUserRoles.setUpdatedAt(auditHelper.getTimeNow());

            userRoles.add(newUserRoles);
        }

        user.setRoles(userRoles);
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setStatus(status);
        user.setIsEnabled(status.getIsAllowed());
        user.setUpdatedAt(auditHelper.getTimeNow());
        user.setUpdatedBy(auditHelper.getUserLogged());

        User userUpdated = userRepository.save(user);

        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(userUpdated);

        log.info("End update user");

        return new ApiResponse<>(true, message, userResponseDTO);

    }

    @Override
    @Observed(event = "USER_GET")
    public ApiResponse<UserResponseDTO> findById(Long id) {

        log.info("Begin find user by id");

        UserResponseDTO userResponseDTO = findEntityDTOById(id);

        String message = messageHelper.getMessage("process.successful.message");

        log.info("End find user by id");

        return new ApiResponse<>(true, message, userResponseDTO);
    }

    @Override
    @Observed(event = "USER_GET_ALL")
    public ApiResponse<List<UserResponseDTO>> findAll() {

        log.info("Begin find all users");

        List<UserResponseDTO> list = findAllEntityDTO();

        String message = messageHelper.getMessage("process.successful.message");

        log.info("End find all users");

        return new ApiResponse<>(true, message, list);
    }


    @Override
    @Observed(event = "USER_DELETE")
    public ApiResponse<Void> remove(Long id) {

        Optional<User> op = userRepository.findById(id);
        if(op.isEmpty()){

            throw new ResourceNotFoundException("USER NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        userRepository.removeUser(id, auditHelper.getUserLogged());

        return new ApiResponse<>(true, message);
    }


    @Override
    public UserResponseDTO findEntityDTOById(Long aLong) {

        User user = findEntityById(aLong);

        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> findAllEntityDTO() {

        List<User> list = findAllEntity();

        return userMapper.listUserToListUserResponseDTO(list);
    }

    @Override
    public User findEntityById(Long aLong) {
        return userRepository.findByIdAndIsDeletedFalse(aLong).orElseThrow(() -> new ResourceNotFoundException("USER NOT FOUND OR NOT EXIST"));
    }

    @Override
    public List<User> findAllEntity() {
        return userRepository.findAllByIsDeletedFalse();
    }
}
