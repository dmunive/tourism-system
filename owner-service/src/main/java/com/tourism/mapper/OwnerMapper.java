package com.tourism.mapper;

import com.tourism.dao.entity.OwnerEntity;
import com.tourism.domain.dto.OwnerDto;
import com.tourism.domain.request.OwnerCreateRequest;
import com.tourism.domain.request.OwnerUpdateRequest;
import org.mapstruct.Mapper;

@Mapper
public interface OwnerMapper {
    OwnerEntity toEntity(OwnerDto owner);
    OwnerDto toDto(OwnerEntity ownerEntity);
    OwnerDto toDto(OwnerCreateRequest ownerCreateRequest);
    OwnerDto toDto(Long idOwner, OwnerUpdateRequest ownerUpdateRequest);
}
