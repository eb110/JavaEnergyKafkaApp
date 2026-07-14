package figura.user_service.dto;

public record UserDto (
    Long id,
    String name,
    String surname,
    String email,
    String address,
    boolean alerting,
    //if alerting is on - we need the energy consumption threshold
    double energyAlertingThreshold
    //email, alerting and threshold are combined for the alerting process
){}