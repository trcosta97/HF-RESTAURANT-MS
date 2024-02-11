package com.hyperfoods.restaurantMicroService.repository;


import com.hyperfoods.restaurantMicroService.entity.FunctioningHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctioningHoursRepository extends JpaRepository<FunctioningHours, Long> {
}
