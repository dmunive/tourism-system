package com.tourism.service.impl;

import com.tourism.dao.OwnerRepository;
import com.tourism.dao.entity.OwnerEntity;
import com.tourism.domain.dto.OwnerDto;
import com.tourism.errors.OwnerNotFoundException;
import com.tourism.mapper.OwnerMapper;
import com.tourism.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerServiceImpl(OwnerRepository ownerRepository,
                            OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    @Override
    public OwnerDto create(OwnerDto owner) {
        OwnerEntity ownerToSave = ownerMapper.toEntity(owner);
        OwnerEntity ownerSaved = ownerRepository.save(ownerToSave);
        return ownerMapper.toDto(ownerSaved);
    }

    @Override
    public OwnerDto get(Long idOwner) {
        OwnerEntity ownerEntity = ownerRepository.findById(idOwner)
                .orElseThrow(OwnerNotFoundException::new);
        return ownerMapper.toDto(ownerEntity);
    }

    @Override
    public OwnerDto update(OwnerDto owner) {
        OwnerEntity ownerToUpdate = ownerMapper.toEntity(owner);
        OwnerEntity ownerUpdated = ownerRepository.save(ownerToUpdate);
        return ownerMapper.toDto(ownerUpdated);
    }

    @Override
    public void delete(Long idOwner) {
        OwnerEntity ownerEntity = ownerRepository.findById(idOwner)
                .orElseThrow(OwnerNotFoundException::new);
        ownerRepository.delete(ownerEntity);
    }

}
