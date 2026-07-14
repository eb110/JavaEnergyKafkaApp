package figura.device_service.service;

import figura.device_service.dto.DeviceDto;
import figura.device_service.entity.Device;
import figura.device_service.exception.DeviceNotFoundException;
import figura.device_service.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDto createDevice(DeviceDto input){
        final Device createdDevice = Device.builder()
            .name(input.name())
            .type(input.type())
            .location(input.location())
            .user_id(input.userId())
            .build();

        final Device device = deviceRepository.save(createdDevice);

        return toDto(device);
    }

    public DeviceDto updateDevice(Long id, DeviceDto input){
        Device device = deviceRepository.findById(id).orElseThrow(
            () -> new DeviceNotFoundException("No device found with id " + id));

        device.setName(input.name());
        device.setType(input.type());
        device.setLocation(input.location());
        device.setUser_id(input.userId());

        final Device updatedDevice = deviceRepository.save(device);

        return toDto(updatedDevice);
    }

    public DeviceDto getDeviceById(Long id) {
        return deviceRepository.findById(id)
            .map(this::toDto).orElseThrow(() ->
                new DeviceNotFoundException("Device not found with id " + id));
    }

    public void deleteDevice(Long id){
        Device device = deviceRepository.findById(id).orElseThrow(
            () -> new DeviceNotFoundException("No device found with id " + id));

        deviceRepository.delete(device);
    }

    private DeviceDto toDto(Device device) {
        return new DeviceDto(
            device.getId(),
            device.getName(),
            device.getType(),
            device.getLocation(),
            device.getUser_id());
    }
}
