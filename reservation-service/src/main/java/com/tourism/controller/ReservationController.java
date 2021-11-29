package com.tourism.controller;

import com.tourism.domain.dto.ReservationDto;
import com.tourism.domain.request.ReservationCreateRequest;
import com.tourism.domain.request.ReservationUpdateRequest;
import com.tourism.mapper.ReservationMapper;
import com.tourism.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService,
                                 ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public ReservationDto create(@RequestBody ReservationCreateRequest request) {
        ReservationDto reservation = reservationMapper.toDto(request);
        return reservationService.create(reservation);
    }

    @GetMapping(path = "/{idReservation}", produces = "application/json")
    public ReservationDto get(@PathVariable Long idReservation) {
        return reservationService.get(idReservation);
    }

    @PutMapping(path = "/{idReservation}", consumes = "application/json", produces = "application/json")
    public ReservationDto update (@PathVariable Long idReservation, @RequestBody ReservationUpdateRequest request) {
        ReservationDto reservation = reservationMapper.toDto(idReservation, request);
        return reservationService.update(reservation);
    }

    @DeleteMapping(path = "/{idReservation}")
    public void delete(@PathVariable Long idReservation) {
        reservationService.delete(idReservation);
    }
}
