package com.tourism.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.domain.dto.CheckInDto;
import com.tourism.domain.dto.OwnerDto;
import com.tourism.domain.dto.ReservationDto;
import com.tourism.domain.message.CheckInPublisherMessage;
import com.tourism.domain.request.ReservationCreateRequest;
import com.tourism.errors.GetRabbitMqMessageFailException;
import com.tourism.errors.SendEmailFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class CheckInSubscriberService {

    private final OwnerService ownerService;
    private final ReservationService reservationService;
    private final MailService emailService;
    private final ObjectMapper objectMapper;
    private final CountDownLatch latch;

    public CheckInSubscriberService(ObjectMapper objectMapper,
                                    OwnerService ownerService,
                                    MailService emailService,
                                    ReservationService reservationService) {
        this.objectMapper = objectMapper;
        this.ownerService = ownerService;
        this.emailService = emailService;
        this.reservationService = reservationService;
        this.latch  = new CountDownLatch(1);
    }

    public void receiveMessage(String message) {
        CheckInPublisherMessage checkInPublisherMessage;
        OwnerDto owner = null;
        ReservationDto reservation;
        CheckInDto checkIn;

        log.info("message: {}", message);

        try {
            checkInPublisherMessage = Optional.ofNullable(objectMapper.readValue(message,
                            CheckInPublisherMessage.class))
                    .orElseThrow(GetRabbitMqMessageFailException::new);
        } catch (Exception e) {
            log.error("error in getting the message", e);
            throw new GetRabbitMqMessageFailException();
        }

        try {
            owner = ownerService.get(checkInPublisherMessage.getIdOwner());

            ReservationCreateRequest reservationCreateRequest = ReservationCreateRequest.builder()
                    .fromDate(checkInPublisherMessage.getFromDate())
                    .toDate(checkInPublisherMessage.getToDate())
                    .idOwner(owner.getId())
                    .numberOfChildren(checkInPublisherMessage.getNumberOfChildren())
                    .numberOfPeople(checkInPublisherMessage.getNumberOfPeople())
                    .numberOfRooms(checkInPublisherMessage.getNumberOfRooms())
                    .build();

            reservation = reservationService.create(reservationCreateRequest);

            checkIn = CheckInDto.builder()
                    .idReservation(reservation.getId())
                    .fromDate(reservation.getFromDate())
                    .toDate(reservation.getToDate())
                    .owner(owner)
                    .numberOfChildren(reservation.getNumberOfChildren())
                    .numberOfRooms(reservation.getNumberOfRooms())
                    .numberOfChildren(reservation.getNumberOfChildren())
                    .build();

            emailService.sendSuccessfulMessage(checkIn);
        } catch (Exception e) {
            log.error("error in processing the message", e);

            if (owner == null) {
                throw e;
            }

            emailService.sendErrorMessage(owner);
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
