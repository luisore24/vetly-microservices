package com.company.microservice_auth.ServiceImpl.menu;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.menu.MenuCreateRequestDTO;
import com.company.microservice_auth.dto.menu.MenuDTO;
import com.company.microservice_auth.dto.menu.MenuResponseDTO;
import com.company.microservice_auth.entity.Menu;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.mapper.MenuMapper;
import com.company.microservice_auth.repository.menu.MenuRepository;
import com.company.microservice_auth.service.menu.MenuInternalDTOService;
import com.company.microservice_auth.service.menu.MenuInternalService;
import com.company.microservice_auth.service.menu.MenuService;
import com.company.microservice_auth.util.AuditHelper;
import com.company.microservice_auth.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService, MenuInternalService, MenuInternalDTOService {

    private final MenuRepository menuRepository;

    private final AuditHelper auditHelper;

    private final MessageHelper messageHelper;

    @Override
    public ApiResponse<MenuResponseDTO> register(MenuCreateRequestDTO entity) {

        Optional<Menu> menu = menuRepository.findByDescription(entity.getDescription());

        if(menu.isPresent()){
            throw new ResourceAlreadyExistException("MENU already exist");
        }


        String message = messageHelper.getMessage("process.successful.message");

        Menu menuCreate = MenuMapper.instance.menuCreateRequestDTOToMenu(entity);

        menuCreate.setCreatedAt(auditHelper.getTimeNow());
        menuCreate.setCreatedBy(auditHelper.getUserLogged());
        menuCreate.setUpdatedAt(auditHelper.getTimeNow());
        menuCreate.setUpdatedBy(auditHelper.getUserLogged());

        Menu menuRegistered = menuRepository.save(menuCreate);

        MenuResponseDTO menuResponseDTO = MenuMapper.instance.menuToMenuResponseDTO(menuRegistered);


        return new ApiResponse<>(true, message, menuResponseDTO);
    }

    @Override
    public ApiResponse<MenuResponseDTO> update(MenuDTO entity) {

        Optional<Menu> menu = menuRepository.findById(entity.getId());

        if(menu.isEmpty()){
            throw new ResourceNotFoundException("MENU NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        Menu menuUpdate = MenuMapper.instance.menuDTOToMenu(entity);
        menuUpdate.setCreatedBy(entity.getCreatedBy());
        menuUpdate.setCreatedAt(entity.getCreatedAt());
        menuUpdate.setUpdatedBy(auditHelper.getUserLogged());
        menuUpdate.setUpdatedAt(auditHelper.getTimeNow());

        Menu menuUpdated = menuRepository.save(menuUpdate);

        MenuResponseDTO menuResponseDTO = MenuMapper.instance.menuToMenuResponseDTO(menuUpdated);

        return new ApiResponse<>(true,message, menuResponseDTO);
    }

    @Override
    public ApiResponse<MenuResponseDTO> findById(Long id) {

        MenuResponseDTO menuResponseDTO = findEntityDTOById(id);

        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true, message, menuResponseDTO);
    }

    @Override
    public ApiResponse<List<MenuResponseDTO>> findAll() {

        List<MenuResponseDTO> list = findAllEntityDTO();
        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true, message, list);
    }

    @Override
    public ApiResponse<Void> remove(Long id) {

        Optional<Menu> menu = menuRepository.findById(id);

        if(menu.isEmpty()){
            throw new ResourceNotFoundException("MENU NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        menuRepository.deleteById(id);

        return new ApiResponse<>(true, message);
    }

    @Override
    public MenuResponseDTO findEntityDTOById(Long aLong) {

        Menu menu = findEntityById(aLong);

        return MenuMapper.instance.menuToMenuResponseDTO(menu);
    }

    @Override
    public List<MenuResponseDTO> findAllEntityDTO() {

        List<Menu> list = findAllEntity();

        return MenuMapper.instance.listMenuToListMenuResponseDTO(list);
    }

    @Override
    public Menu findEntityById(Long aLong) {
        return menuRepository.findById(aLong).orElseThrow(() -> new ResourceNotFoundException("MENU NOT FOUND"));
    }

    @Override
    public List<Menu> findAllEntity() {
        return menuRepository.findAll();
    }
}
