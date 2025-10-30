package com.company.microservice_auth.service.permission;

import com.company.microservice_auth.dto.permission.PermissionCreateRequestDTO;
import com.company.microservice_auth.dto.permission.PermissionDTO;
import com.company.microservice_auth.dto.permission.PermissionResponseDTO;
import com.company.microservice_auth.service.CommonService;

public interface PermissionService extends CommonService<PermissionCreateRequestDTO, PermissionDTO, PermissionResponseDTO> {
}
