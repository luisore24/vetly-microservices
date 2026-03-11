package com.company.microservice_auth.ServiceImpl.status;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.status.StatusCreateRequestDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import com.company.microservice_auth.dto.status.StatusResponseDTO;
import com.company.microservice_auth.entity.Status;
import com.company.microservice_auth.exception.common.ResourceAlreadyExistException;
import com.company.microservice_auth.exception.common.ResourceNotFoundException;
import com.company.microservice_auth.mapper.StatusMapper;
import com.company.microservice_auth.repository.status.StatusRepository;
import com.company.microservice_auth.service.status.StatusInternalDTOService;
import com.company.microservice_auth.service.status.StatusInternalService;
import com.company.microservice_auth.service.status.StatusService;
import com.company.microservice_auth.util.AuditHelper;
import com.company.microservice_auth.util.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService, StatusInternalService, StatusInternalDTOService {

    private final StatusRepository statusRepository;

    private final AuditHelper auditHelper;

    private final MessageHelper messageHelper;

    @Override
    public ApiResponse<StatusResponseDTO> register(StatusCreateRequestDTO entity) {

        Optional<Status> status = statusRepository.findByDescription(entity.getDescription());

        if(status.isPresent()){
            throw new ResourceAlreadyExistException("STATUS already exist");
        }


        String message = messageHelper.getMessage("process.successful.message");

        Status statusCreate = StatusMapper.instance.statusCreateRequestDTOToStatus(entity);

        statusCreate.setCreatedAt(auditHelper.getTimeNow());
        statusCreate.setCreatedBy(auditHelper.getUserLogged());
        statusCreate.setUpdatedAt(auditHelper.getTimeNow());
        statusCreate.setUpdatedBy(auditHelper.getUserLogged());

        Status statusRegistered = statusRepository.save(statusCreate);

        StatusResponseDTO statusResponseDTO = StatusMapper.instance.statusToStatusResponseDTO(statusRegistered);


        return new ApiResponse<>(true, message, statusResponseDTO);
    }

    @Override
    public ApiResponse<StatusResponseDTO> update(StatusDTO entity) {

        Optional<Status> status = statusRepository.findById(entity.getId());

        if(status.isEmpty()){
            throw new ResourceNotFoundException("STATUS NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        Status statusUpdate = StatusMapper.instance.statusDTOToStatus(entity);
        statusUpdate.setCreatedBy(entity.getCreatedBy());
        statusUpdate.setCreatedAt(entity.getCreatedAt());
        statusUpdate.setUpdatedBy(auditHelper.getUserLogged());
        statusUpdate.setUpdatedAt(auditHelper.getTimeNow());

        Status statusUpdated = statusRepository.save(statusUpdate);

        StatusResponseDTO statusResponseDTO = StatusMapper.instance.statusToStatusResponseDTO(statusUpdated);

        return new ApiResponse<>(true,message, statusResponseDTO);
    }

    @Override
    public ApiResponse<StatusResponseDTO> findById(Long id) {


        StatusResponseDTO statusResponseDTO = findEntityDTOById(id);

        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true, message, statusResponseDTO);

    }

    @Override
    public ApiResponse<List<StatusResponseDTO>> findAll() {

        List<StatusResponseDTO> list = findAllEntityDTO();
        String message = messageHelper.getMessage("process.successful.message");

        return new ApiResponse<>(true,message,list);
    }


    @Override
    public ApiResponse<Void> remove(Long id) {

        Optional<Status> status = statusRepository.findById(id);

        if(status.isEmpty()){
            throw new ResourceNotFoundException("STATUS NOT FOUND");
        }

        String message = messageHelper.getMessage("process.successful.message");

        statusRepository.deleteById(id);

        return new ApiResponse<>(true, message);
    }


    @Override
    public StatusResponseDTO findEntityDTOById(Long id) {

        Status status = findEntityById(id);

        return StatusMapper.instance.statusToStatusResponseDTO(status);

    }

    @Override
    public List<StatusResponseDTO> findAllEntityDTO() {
        System.out.println("Print in All EntityDTO");
        List<Status> list = findAllEntity();
        return StatusMapper.instance.listStatusToListStatusResponseDTO(list);
    }

    @Override
    public Status findEntityById(Long id) {

        return statusRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("STATUS not found"));

    }

    @Override
    public List<Status> findAllEntity() {
        System.out.println("Print in All Entity");
        return statusRepository.findAll();
    }
}
