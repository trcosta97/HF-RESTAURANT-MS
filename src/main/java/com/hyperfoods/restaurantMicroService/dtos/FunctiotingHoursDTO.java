package com.hyperfoods.restaurantMicroService.dtos;



import java.time.LocalDateTime;

public record FunctiotingHoursDTO (
        LocalDateTime openingTime,
        LocalDateTime closingTime
) {
}

