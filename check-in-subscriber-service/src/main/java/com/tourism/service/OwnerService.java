package com.tourism.service;

import com.tourism.domain.dto.OwnerDto;
import com.tourism.errors.OwnerServiceFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class OwnerService {

    private final RestTemplate ownerServiceRestTemplate;

    public OwnerService(RestTemplate ownerServiceRestTemplate) {
        this.ownerServiceRestTemplate = ownerServiceRestTemplate;
    }

    public OwnerDto get(Long idOwner) {
        OwnerDto owner;

        try {
            UriComponents uriOwner = UriComponentsBuilder.newInstance()
                    .path("/")
                    .path(idOwner.toString())
                    .build();

            owner = ownerServiceRestTemplate.getForObject(uriOwner.toString(), OwnerDto.class);
        } catch (Exception e) {
            log.error("error in calling owner service", e);
            throw new OwnerServiceFailException();
        }

        return owner;
    }
}
