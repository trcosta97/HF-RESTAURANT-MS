package com.hyperfoods.restaurantMicroService.dtos;

import com.hyperfoods.restaurantMicroService.entity.Restaurant;

public record RestaurantListDTO(
        String nome,
        String description
) {

    public RestaurantListDTO(Restaurant restaurant) {
        this(restaurant.getName(), restaurant.getDescription());
    }
}