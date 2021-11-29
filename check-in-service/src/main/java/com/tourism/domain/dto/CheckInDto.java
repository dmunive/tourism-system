package com.tourism.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CheckInDto {
    private Long idReservation;
    private LocalDateTime toDate;
    private LocalDateTime fromDate;
    private Short numberOfPeople;
    private OwnerDto owner;
    private Short numberOfRooms;
    private Short numberOfChildren;
}
