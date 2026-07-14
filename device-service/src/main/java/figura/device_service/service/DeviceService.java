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
            .name(input.getName())
            .type(input.getType())
            .location(input.getLocation())
            .user_id(input.getUserId())
            .build();

        final Device device = deviceRepository.save(createdDevice);

        return toDto(device);
    }

    public DeviceDto updateDevice(Long id, DeviceDto input){
        Device device = deviceRepository.findById(id).orElseThrow(
            () -> new DeviceNotFoundException("No device found with id " + id));

        device.setName(input.getName());
        device.setType(input.getType());
        device.setLocation(input.getLocation());
        device.setUser_id(input.getUserId());

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
        return DeviceDto.builder()
            .id(device.getId())
            .name(device.getName())
            .type(device.getType())
            .location(device.getLocation())
            .userId(device.getUser_id())
            .build();
    }
}
