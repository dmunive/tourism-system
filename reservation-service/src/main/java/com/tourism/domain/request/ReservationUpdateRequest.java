package com.tourism.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationUpdateRequest {
    private LocalDateTime toDate;
    private LocalDateTime fromDate;
    private Short numberOfPeople;
    private Long idOwner;
    private Short numberOfRooms;
    private Short numberOfChildren;
}
