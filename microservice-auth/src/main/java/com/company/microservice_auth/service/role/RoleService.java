package com.company.microservice_auth.service.role;

import com.company.microservice_auth.dto.role.RoleCreateRequestDTO;
import com.company.microservice_auth.dto.role.RoleResponseDTO;
import com.company.microservice_auth.dto.role.RoleUpdateRequestDTO;
import com.company.microservice_auth.service.CommonService;

public interface RoleService extends CommonService<RoleCreateRequestDTO, RoleUpdateRequestDTO, RoleResponseDTO> {
}
