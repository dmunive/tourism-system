package com.tourism.domain.request;

import lombok.Data;

@Data
public class OwnerUpdateRequest {
    private String name;
    private String lastName;
    private String email;
    private String identificationNumber;
}
