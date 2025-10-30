package com.company.microservice_auth.controller.role;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.role.RoleCreateRequestDTO;
import com.company.microservice_auth.dto.role.RoleDTO;
import com.company.microservice_auth.dto.role.RoleResponseDTO;
import com.company.microservice_auth.dto.role.RoleUpdateRequestDTO;
import com.company.microservice_auth.dto.status.StatusResponseDTO;
import com.company.microservice_auth.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> registerRole(@RequestBody RoleCreateRequestDTO roleCreateRequestDTO){

        ApiResponse<RoleResponseDTO> roleResponseDTO = roleService.register(roleCreateRequestDTO);

        return new ResponseEntity<>(roleResponseDTO, HttpStatusCode.valueOf(201));

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> updateRole(@RequestBody RoleUpdateRequestDTO roleUpdateRequestDTO){

        ApiResponse<RoleResponseDTO> roleResponseDTO = roleService.update(roleUpdateRequestDTO);

        return new ResponseEntity<>(roleResponseDTO, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable("id") Long roleId){

        ApiResponse<Void> roleDeleted = roleService.remove(roleId);

        return new ResponseEntity<>(roleDeleted, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> findById(@Param("id") Long id){

        ApiResponse<RoleResponseDTO> response = roleService.findById(id);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<RoleResponseDTO>>> findAll(){

        ApiResponse<List<RoleResponseDTO>> response = roleService.findAll();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
