package figura.device_service.controller;

import figura.device_service.dto.DeviceDto;
import figura.device_service.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @PostMapping("create")
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto){
        DeviceDto created = deviceService.createDevice(deviceDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getById(@PathVariable Long id){
            DeviceDto deviceDto = deviceService.getDeviceById(id);
            return new ResponseEntity<>(deviceDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto){
            DeviceDto updatedDevice = deviceService.updateDevice(id, deviceDto);
            return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable Long id){
        try {
            deviceService.deleteDevice(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>("Device not found", HttpStatus.NOT_FOUND);
        }
    }

}
