package com.company.microservice_auth.service.menu;

import com.company.microservice_auth.dto.menu.MenuCreateRequestDTO;
import com.company.microservice_auth.dto.menu.MenuDTO;
import com.company.microservice_auth.dto.menu.MenuResponseDTO;
import com.company.microservice_auth.service.CommonService;

public interface MenuService extends CommonService<MenuCreateRequestDTO, MenuDTO, MenuResponseDTO> {
}
