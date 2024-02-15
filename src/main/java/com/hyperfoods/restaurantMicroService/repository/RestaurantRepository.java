package com.hyperfoods.restaurantMicroService.repository;

import com.hyperfoods.restaurantMicroService.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Page<Restaurant> findAll(Pageable pageable);

    Page<Restaurant> findAllByActiveTrue(Pageable pageable);

    Optional<Restaurant> findByIdAndActiveTrue(Long id);
}
