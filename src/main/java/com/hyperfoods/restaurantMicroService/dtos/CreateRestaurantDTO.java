package com.hyperfoods.restaurantMicroService.dtos;


import jakarta.validation.constraints.NotBlank;

public record CreateRestaurantDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,

        CreateAddressDTO address


) {
}
