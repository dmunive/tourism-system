package com.tourism.service.impl;

import com.tourism.domain.dto.CheckInDto;
import com.tourism.domain.dto.OwnerDto;
import com.tourism.domain.dto.ReservationDto;
import com.tourism.domain.message.CheckInPublisherMessage;
import com.tourism.errors.CheckInPublisherServiceException;
import com.tourism.errors.OwnerServiceFailException;
import com.tourism.errors.ReservationServiceFailException;
import com.tourism.publisher.CheckInPublisher;
import com.tourism.service.CheckInService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckInService {

    private final RestTemplate ownerServiceRestTemplate;
    private final RestTemplate reservationServiceRestTemplate;
    private final CheckInPublisher checkInPublisher;

    public CheckInServiceImpl(RestTemplate ownerServiceRestTemplate,
                              RestTemplate reservationServiceRestTemplate,
                              CheckInPublisher checkInPublisher) {
        this.ownerServiceRestTemplate = ownerServiceRestTemplate;
        this.reservationServiceRestTemplate = reservationServiceRestTemplate;
        this.checkInPublisher = checkInPublisher;
    }

    @Override
    public void create(CheckInPublisherMessage checkIn) {
        try {
            checkInPublisher.publish(checkIn);
        } catch (Exception e) {
            throw new CheckInPublisherServiceException();
        }
    }

    @Override
    public CheckInDto get(Long idReservation) {
        ReservationDto reservation;
        OwnerDto owner;

        try {
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .path("/")
                    .path(idReservation.toString())
                    .build();

            reservation = Optional.ofNullable(reservationServiceRestTemplate.getForObject(uriComponents.toString(),
                            ReservationDto.class))
                    .orElseThrow(ReservationServiceFailException::new);
        } catch (Exception e) {
            throw new ReservationServiceFailException();
        }
        try {
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .path("/")
                    .path(reservation.getIdOwner().toString())
                    .build();

            owner = Optional.ofNullable(ownerServiceRestTemplate.getForObject(uriComponents.toString(),
                            OwnerDto.class))
                    .orElseThrow(OwnerServiceFailException::new);
        } catch (Exception e) {
            throw new OwnerServiceFailException();
        }

        return CheckInDto.builder()
                .idReservation(reservation.getId())
                .fromDate(reservation.getFromDate())
                .toDate(reservation.getToDate())
                .owner(owner)
                .numberOfChildren(reservation.getNumberOfChildren())
                .numberOfRooms(reservation.getNumberOfRooms())
                .numberOfChildren(reservation.getNumberOfChildren())
                .build();
    }
}
