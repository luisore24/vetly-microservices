package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.menu.MenuCreateRequestDTO;
import com.company.microservice_auth.dto.menu.MenuDTO;
import com.company.microservice_auth.dto.menu.MenuResponseDTO;
import com.company.microservice_auth.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuMapper {

    MenuMapper instance = Mappers.getMapper(MenuMapper.class);

    Menu menuCreateRequestDTOToMenu(MenuCreateRequestDTO requestDTO);

    Menu menuDTOToMenu(MenuDTO menuDTO);

    MenuResponseDTO menuToMenuResponseDTO(Menu menu);

    List<MenuResponseDTO> listMenuToListMenuResponseDTO(List<Menu> list);

    
}
