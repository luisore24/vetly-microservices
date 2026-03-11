package com.company.microservice_auth.service.status;

import com.company.microservice_auth.dto.status.StatusCreateRequestDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import com.company.microservice_auth.dto.status.StatusResponseDTO;
import com.company.microservice_auth.service.CommonService;

public interface StatusService extends CommonService<StatusCreateRequestDTO, StatusDTO, StatusResponseDTO> {
}
