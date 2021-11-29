package com.tourism.controller;

import com.tourism.domain.request.OwnerUpdateRequest;
import com.tourism.mapper.OwnerMapper;
import com.tourism.service.OwnerService;
import com.tourism.domain.dto.OwnerDto;
import com.tourism.domain.request.OwnerCreateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/owner")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService,
                           OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public OwnerDto create(@RequestBody OwnerCreateRequest request) {
        OwnerDto owner = ownerMapper.toDto(request);
        return ownerService.create(owner);
    }

    @GetMapping(path = "/{idOwner}", produces = "application/json")
    public OwnerDto get(@PathVariable Long idOwner) {
        return ownerService.get(idOwner);
    }

    @PutMapping(path = "/{idOwner}", consumes = "application/json", produces = "application/json")
    public OwnerDto update (@PathVariable Long idOwner, @RequestBody OwnerUpdateRequest request) {
        OwnerDto owner = ownerMapper.toDto(idOwner, request);
        return ownerService.update(owner);
    }

    @DeleteMapping(path = "/{idOwner}")
    public void delete(@PathVariable Long idOwner) {
        ownerService.delete(idOwner);
    }
}
