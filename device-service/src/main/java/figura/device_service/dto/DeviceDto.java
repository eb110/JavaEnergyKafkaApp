package figura.device_service.dto;

import figura.device_service.model.DeviceType;

public record DeviceDto (

    Long id,
    String name,
    DeviceType type,
    String location,
    Long userId
){}
