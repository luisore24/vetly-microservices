package com.company.microservice_auth.ServiceImpl.role;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.role.RoleCreateRequestDTO;
import com.company.microservice_auth.dto.role.RoleResponseDTO;
import com.company.microservice_auth.dto.role.RoleUpdateRequestDTO;
import com.company.microservice_auth.entity.Role;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.mapper.RoleMapper;
import com.company.microservice_auth.repository.role.RoleRepository;
import com.company.microservice_auth.service.role.RoleInternalDTOService;
import com.company.microservice_auth.service.role.RoleInternalService;
import com.company.microservice_auth.service.role.RoleService;
import com.company.microservice_auth.util.AuditHelper;
import com.company.microservice_auth.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService, RoleInternalService, RoleInternalDTOService {

    private final RoleRepository roleRepository;

    private final AuditHelper auditHelper;

    private final MessageHelper messageHelper;

    @Override
    public ApiResponse<RoleResponseDTO> register(RoleCreateRequestDTO entity) {

        Optional<Role> role = roleRepository.findByDescription(entity.getDescription());

        if(role.isPresent()){
            throw new ResourceAlreadyExistException("ROLE already exist");
        }


        String message = messageHelper.getMessage("process.successful.message");

        Role roleCreate = RoleMapper.instance.roleCreateRequestDTOToRole(entity);

        roleCreate.setCreatedAt(auditHelper.getTimeNow());
        roleCreate.setCreatedBy(auditHelper.getUserLogged());
        roleCreate.setUpdatedAt(auditHelper.getTimeNow());
        roleCreate.setUpdatedBy(auditHelper.getUserLogged());

        Role roleRegistered = roleRepository.save(roleCreate);

        RoleResponseDTO roleResponseDTO = RoleMapper.instance.roleToRoleResponseDTO(roleRegistered);


        return new ApiResponse<>(true, message, roleResponseDTO);
    }

    @Override
    public ApiResponse<RoleResponseDTO> update(RoleUpdateRequestDTO entity) {

        Optional<Role> role = roleRepository.findById(entity.getId());

        if(role.isEmpty()){
            throw new ResourceNotFoundException("ROLE NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        Role roleUpdate = RoleMapper.instance.roleDTOToRole(entity);
        roleUpdate.setUpdatedBy(auditHelper.getUserLogged());
        roleUpdate.setUpdatedAt(auditHelper.getTimeNow());

        Role roleUpdated = roleRepository.save(roleUpdate);

        RoleResponseDTO roleResponseDTO = RoleMapper.instance.roleToRoleResponseDTO(roleUpdated);

        return new ApiResponse<>(true,message, roleResponseDTO);
    }

    @Override
    public ApiResponse<RoleResponseDTO> findById(Long id) {

        RoleResponseDTO roleResponseDTO = findEntityDTOById(id);

        String message = messageHelper.getMessage("process.successful.message");
        return new ApiResponse<>(true, message, roleResponseDTO);
    }

    @Override
    public ApiResponse<List<RoleResponseDTO>> findAll() {

        List<RoleResponseDTO> list = findAllEntityDTO();
        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true, message, list);
    }

    @Override
    public ApiResponse<Void> remove(Long id) {

        Optional<Role> role = roleRepository.findById(id);

        if(role.isEmpty()){
            throw new ResourceNotFoundException("ROLE NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        roleRepository.deleteById(id);

        return new ApiResponse<>(true, message);
    }


    @Override
    public RoleResponseDTO findEntityDTOById(Long aLong) {

        Role role = findEntityById(aLong);

        return RoleMapper.instance.roleToRoleResponseDTO(role);
    }

    @Override
    public List<RoleResponseDTO> findAllEntityDTO() {

        List<Role> list = findAllEntity();

        return RoleMapper.instance.listRoleToListRoleResponseDTO(list);
    }

    @Override
    public Role findEntityById(Long aLong) {
        return roleRepository.findById(aLong).orElseThrow( () -> new ResourceNotFoundException("ROLE not found"));
    }

    @Override
    public List<Role> findAllEntity() {
        return roleRepository.findAll();
    }
}
