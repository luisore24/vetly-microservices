package com.company.microservice_auth.mapper;

import com.company.microservice_auth.dto.status.StatusCreateRequestDTO;
import com.company.microservice_auth.dto.status.StatusDTO;
import com.company.microservice_auth.dto.status.StatusResponseDTO;
import com.company.microservice_auth.entity.Status;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface StatusMapper {

    StatusMapper instance = Mappers.getMapper(StatusMapper.class);

    Status statusCreateRequestDTOToStatus(StatusCreateRequestDTO requestDTO);

    Status statusDTOToStatus(StatusDTO statusDTO);

    StatusResponseDTO statusToStatusResponseDTO(Status status);

    List<StatusResponseDTO> listStatusToListStatusResponseDTO(List<Status> list);

}

