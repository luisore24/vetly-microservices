package com.company.microservice_auth.ServiceImpl.permission;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.permission.PermissionCreateRequestDTO;
import com.company.microservice_auth.dto.permission.PermissionDTO;
import com.company.microservice_auth.dto.permission.PermissionResponseDTO;
import com.company.microservice_auth.entity.Permission;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.mapper.PermissionMapper;
import com.company.microservice_auth.repository.permission.PermissionRepository;
import com.company.microservice_auth.service.permission.PermissionInternalDTOService;
import com.company.microservice_auth.service.permission.PermissionInternalService;
import com.company.microservice_auth.service.permission.PermissionService;
import com.company.microservice_auth.util.AuditHelper;
import com.company.microservice_auth.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService, PermissionInternalService, PermissionInternalDTOService {

    private final PermissionRepository permissionRepository;

    private final AuditHelper auditHelper;

    private final MessageHelper messageHelper;

    @Override
    public ApiResponse<PermissionResponseDTO> register(PermissionCreateRequestDTO entity) {

        Optional<Permission> permission = permissionRepository.findByDescription(entity.getDescription());

        if(permission.isPresent()){
            throw new ResourceAlreadyExistException("PERMISSION already exist");
        }


        String message = messageHelper.getMessage("process.successful.message");

        Permission permissionCreate = PermissionMapper.instance.permissionCreateRequestDTOToPermission(entity);

        permissionCreate.setCreatedAt(auditHelper.getTimeNow());
        permissionCreate.setCreatedBy(auditHelper.getUserLogged());
        permissionCreate.setUpdatedAt(auditHelper.getTimeNow());
        permissionCreate.setUpdatedBy(auditHelper.getUserLogged());

        Permission permissionRegistered = permissionRepository.save(permissionCreate);

        PermissionResponseDTO permissionResponseDTO = PermissionMapper.instance.permissionToPermissionResponseDTO(permissionRegistered);


        return new ApiResponse<>(true, message, permissionResponseDTO);
    }

    @Override
    public ApiResponse<PermissionResponseDTO> update(PermissionDTO entity) {

        Optional<Permission> permission = permissionRepository.findById(entity.getId());

        if(permission.isEmpty()){
            throw new ResourceNotFoundException("PERMISSION NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        Permission permissionUpdate = PermissionMapper.instance.permissionDTOToPermission(entity);
        permissionUpdate.setCreatedBy(entity.getCreatedBy());
        permissionUpdate.setCreatedAt(entity.getCreatedAt());
        permissionUpdate.setUpdatedBy(auditHelper.getUserLogged());
        permissionUpdate.setUpdatedAt(auditHelper.getTimeNow());

        Permission permissionUpdated = permissionRepository.save(permissionUpdate);

        PermissionResponseDTO permissionResponseDTO = PermissionMapper.instance.permissionToPermissionResponseDTO(permissionUpdated);

        return new ApiResponse<>(true,message, permissionResponseDTO);
    }

    @Override
    public ApiResponse<PermissionResponseDTO> findById(Long id) {

        PermissionResponseDTO permissionResponseDTO = findEntityDTOById(id);
        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true, message, permissionResponseDTO);
    }

    @Override
    public ApiResponse<List<PermissionResponseDTO>> findAll() {

        List<PermissionResponseDTO> list = findAllEntityDTO();
        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true, message, list);
    }

    @Override
    public ApiResponse<Void> remove(Long id) {

        Optional<Permission> permission = permissionRepository.findById(id);

        if(permission.isEmpty()){
            throw new ResourceNotFoundException("PERMISSION NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        permissionRepository.deleteById(id);

        return new ApiResponse<>(true, message);
    }

    @Override
    public PermissionResponseDTO findEntityDTOById(Long aLong) {

        Permission permission = findEntityById(aLong);

        return PermissionMapper.instance.permissionToPermissionResponseDTO(permission);
    }

    @Override
    public List<PermissionResponseDTO> findAllEntityDTO() {

        List<Permission> list = findAllEntity();

        return PermissionMapper.instance.listPermissionToListPermissionResponseDTO(list);
    }

    @Override
    public Permission findEntityById(Long aLong) {
        return permissionRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("PERMISSION not found"));
    }

    @Override
    public List<Permission> findAllEntity() {
        return permissionRepository.findAll();
    }
}
