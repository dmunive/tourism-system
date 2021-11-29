package com.tourism.mapper;

import com.tourism.dao.entity.ReservationEntity;
import com.tourism.domain.dto.ReservationDto;
import com.tourism.domain.request.ReservationCreateRequest;
import com.tourism.domain.request.ReservationUpdateRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ReservationMapper {
    ReservationEntity toEntity(ReservationDto reservation);
    ReservationDto toDto(ReservationEntity reservationEntity);
    ReservationDto toDto(ReservationCreateRequest reservationCreateRequest);
    ReservationDto toDto(Long idReservation, ReservationUpdateRequest reservationUpdateRequest);
}
