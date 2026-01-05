package com.company.microservice_auth.controller.menu;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.menu.MenuCreateRequestDTO;
import com.company.microservice_auth.dto.menu.MenuDTO;
import com.company.microservice_auth.dto.menu.MenuResponseDTO;
import com.company.microservice_auth.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> registerMenu(@RequestBody MenuCreateRequestDTO menuCreateRequestDTO){

        ApiResponse<MenuResponseDTO> menuResponseDTO = menuService.register(menuCreateRequestDTO);

        return new ResponseEntity<>(menuResponseDTO, HttpStatusCode.valueOf(201));

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> updateMenu(@RequestBody MenuDTO menuRequestDTO){

        ApiResponse<MenuResponseDTO> menuResponseDTO = menuService.update(menuRequestDTO);

        return new ResponseEntity<>(menuResponseDTO, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMenu(@PathVariable("id") Long menuId){

        ApiResponse<Void> menuDeleted = menuService.remove(menuId);

        return new ResponseEntity<>(menuDeleted, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> findById(@Param("id") Long id){

        ApiResponse<MenuResponseDTO> response = menuService.findById(id);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> findAll(){

        ApiResponse<List<MenuResponseDTO>> response = menuService.findAll();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
