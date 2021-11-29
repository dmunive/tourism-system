package com.tourism.service;

import com.tourism.domain.dto.OwnerDto;


public interface OwnerService {

    OwnerDto create(OwnerDto owner);
    OwnerDto get(Long idOwner);
    OwnerDto update(OwnerDto owner);
    void delete(Long idOwner);

}
