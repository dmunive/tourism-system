package com.tourism.service.impl;

import com.tourism.dao.ReservationRepository;
import com.tourism.dao.entity.ReservationEntity;
import com.tourism.domain.dto.ReservationDto;
import com.tourism.errors.ReservationNotFoundException;
import com.tourism.mapper.ReservationMapper;
import com.tourism.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDto create(ReservationDto reservation) {
        ReservationEntity reservationToSave = reservationMapper.toEntity(reservation);
        ReservationEntity reservationSaved = reservationRepository.save(reservationToSave);
        return reservationMapper.toDto(reservationSaved);
    }

    @Override
    public ReservationDto get(Long idReservation) {
        ReservationEntity reservationEntity = reservationRepository.findById(idReservation)
                .orElseThrow(ReservationNotFoundException::new);
        return reservationMapper.toDto(reservationEntity);
    }

    @Override
    public ReservationDto update(ReservationDto reservation) {
        ReservationEntity reservationToUpdate = reservationMapper.toEntity(reservation);
        ReservationEntity reservationUpdated = reservationRepository.save(reservationToUpdate);
        return reservationMapper.toDto(reservationUpdated);
    }

    @Override
    public void delete(Long idReservation) {
        ReservationEntity reservationEntity = reservationRepository.findById(idReservation)
                .orElseThrow(ReservationNotFoundException::new);
        reservationRepository.delete(reservationEntity);
    }

}
