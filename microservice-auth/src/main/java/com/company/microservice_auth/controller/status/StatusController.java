package com.company.microservice_auth.controller.status;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.status.StatusCreateRequestDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import com.company.microservice_auth.dto.status.StatusResponseDTO;
import com.company.microservice_auth.service.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @PreAuthorize("hasAuthority('WRITE_STATUS')")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<StatusResponseDTO>> registerStatus(@RequestBody StatusCreateRequestDTO statusCreateRequestDTO){

        ApiResponse<StatusResponseDTO> statusResponseDTO = statusService.register(statusCreateRequestDTO);

        return new ResponseEntity<>(statusResponseDTO, HttpStatusCode.valueOf(201));

    }

    @PreAuthorize("hasAuthority('UPDATE_STATUS')")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<StatusResponseDTO>> updateStatus(@RequestBody StatusDTO statusRequestDTO){

        ApiResponse<StatusResponseDTO> statusResponseDTO = statusService.update(statusRequestDTO);

        return new ResponseEntity<>(statusResponseDTO, HttpStatusCode.valueOf(200));
    }

    @PreAuthorize("hasAuthority('DELETE_STATUS')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStatus(@PathVariable("id") Long statusId){

        ApiResponse<Void> statusDeleted = statusService.remove(statusId);

        return new ResponseEntity<>(statusDeleted, HttpStatusCode.valueOf(200));

    }

    @PreAuthorize("hasAuthority('READ_STATUS')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StatusResponseDTO>> findById(@Param("id") Long id){

        ApiResponse<StatusResponseDTO> response = statusService.findById(id);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PreAuthorize("hasAuthority('READ_STATUS')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<StatusResponseDTO>>> findAll(){
        System.out.println("Print in controller");

        ApiResponse<List<StatusResponseDTO>> response = statusService.findAll();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
