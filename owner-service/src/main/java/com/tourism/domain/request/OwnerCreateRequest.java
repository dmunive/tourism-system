package com.tourism.domain.request;

import lombok.Data;

@Data
public class OwnerCreateRequest {
    private String name;
    private String lastName;
    private String email;
    private String identificationNumber;
}
