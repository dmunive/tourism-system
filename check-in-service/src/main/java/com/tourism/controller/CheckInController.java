package com.tourism.controller;

import com.tourism.domain.dto.CheckInDto;
import com.tourism.domain.request.CheckInCreateRequest;
import com.tourism.domain.message.CheckInPublisherMessage;
import com.tourism.mapper.CheckInMapper;
import com.tourism.service.CheckInService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/check-in")
public class CheckInController {

    private final CheckInService checkInService;
    private final CheckInMapper checkInMapper;

    public CheckInController(CheckInService checkInService,
                             CheckInMapper checkInMapper) {
        this.checkInService = checkInService;
        this.checkInMapper = checkInMapper;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public void create(@RequestBody CheckInCreateRequest request) {
        CheckInPublisherMessage checkIn = checkInMapper.toRequest(request);
        checkInService.create(checkIn);
    }

    @GetMapping(path = "/{idReservation}", produces = "application/json")
    public CheckInDto get(@PathVariable Long idReservation) {
        return checkInService.get(idReservation);
    }

}
