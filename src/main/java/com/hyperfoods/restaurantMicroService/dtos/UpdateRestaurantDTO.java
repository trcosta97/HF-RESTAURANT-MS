package com.hyperfoods.restaurantMicroService.dtos;


import jakarta.validation.constraints.NotBlank;

public record UpdateRestaurantDTO(

        String name,

        String description


) {
}
