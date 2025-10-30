package com.company.microservice_auth.controller.permission;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.permission.PermissionCreateRequestDTO;
import com.company.microservice_auth.dto.permission.PermissionDTO;
import com.company.microservice_auth.dto.permission.PermissionResponseDTO;
import com.company.microservice_auth.dto.role.RoleResponseDTO;
import com.company.microservice_auth.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> registerPermission(@RequestBody PermissionCreateRequestDTO permissionCreateRequestDTO){

        ApiResponse<PermissionResponseDTO> permissionResponseDTO = permissionService.register(permissionCreateRequestDTO);

        return new ResponseEntity<>(permissionResponseDTO, HttpStatusCode.valueOf(201));

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> updatePermission(@RequestBody PermissionDTO permissionRequestDTO){

        ApiResponse<PermissionResponseDTO> permissionResponseDTO = permissionService.update(permissionRequestDTO);

        return new ResponseEntity<>(permissionResponseDTO, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable("id") Long permissionId){

        ApiResponse<Void> permissionDeleted = permissionService.remove(permissionId);

        return new ResponseEntity<>(permissionDeleted, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> findById(@Param("id") Long id){

        ApiResponse<PermissionResponseDTO> response = permissionService.findById(id);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PermissionResponseDTO>>> findAll(){

        ApiResponse<List<PermissionResponseDTO>> response = permissionService.findAll();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
