package com.tourism.mapper;

import com.tourism.domain.request.CheckInCreateRequest;
import com.tourism.domain.message.CheckInPublisherMessage;
import org.mapstruct.Mapper;

@Mapper
public interface CheckInMapper {
    CheckInPublisherMessage toRequest(CheckInCreateRequest checkInCreateRequest);
}
