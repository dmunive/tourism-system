package com.tourism.service;

import com.tourism.domain.dto.ReservationDto;

public interface ReservationService {

    ReservationDto create(ReservationDto owner);
    ReservationDto get(Long idReservation);
    ReservationDto update(ReservationDto owner);
    void delete(Long idReservation);

}
