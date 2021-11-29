package com.tourism.service;

import com.tourism.domain.dto.ReservationDto;
import com.tourism.domain.request.ReservationCreateRequest;
import com.tourism.errors.ReservationServiceFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ReservationService {

    private final RestTemplate reservationServiceRestTemplate;

    public ReservationService(RestTemplate reservationServiceRestTemplate) {
        this.reservationServiceRestTemplate = reservationServiceRestTemplate;
    }

    public ReservationDto create(ReservationCreateRequest reservationCreateRequest)  {
        ReservationDto reservation;

        try {
            UriComponents uriReservation = UriComponentsBuilder.newInstance()
                    .path("/")
                    .build();

            reservation = reservationServiceRestTemplate.postForObject(uriReservation.toString(),
                    reservationCreateRequest, ReservationDto.class);
        } catch (Exception e) {
            log.error("error in calling reservation service", e);
            throw new ReservationServiceFailException();
        }

        return reservation;
    }
}
