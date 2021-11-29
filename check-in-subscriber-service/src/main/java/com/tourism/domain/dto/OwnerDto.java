package com.tourism.domain.dto;

import lombok.Data;

@Data
public class OwnerDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String identificationNumber;
}
