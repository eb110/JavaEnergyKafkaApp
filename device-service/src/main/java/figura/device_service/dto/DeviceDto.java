package figura.device_service.dto;

import figura.device_service.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

    private Long id;
    private String name;
    private DeviceType type;
    private String location;
    private Long userId;
}
