package com.tourism.service;

import com.tourism.domain.dto.CheckInDto;
import com.tourism.domain.message.CheckInPublisherMessage;

public interface CheckInService {
    void create(CheckInPublisherMessage request);
    CheckInDto get(Long idReservation);
}
