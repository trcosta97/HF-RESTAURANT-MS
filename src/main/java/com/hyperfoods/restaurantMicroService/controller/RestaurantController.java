package com.hyperfoods.restaurantMicroService.controller;

import com.hyperfoods.restaurantMicroService.dtos.CreateRestaurantDTO;
import com.hyperfoods.restaurantMicroService.dtos.FunctiotingHoursDTO;
import com.hyperfoods.restaurantMicroService.dtos.RestaurantListDTO;
import com.hyperfoods.restaurantMicroService.dtos.UpdateRestaurantDTO;
import com.hyperfoods.restaurantMicroService.entity.FunctioningHours;
import com.hyperfoods.restaurantMicroService.entity.Restaurant;
import com.hyperfoods.restaurantMicroService.service.RestaurantService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @PostMapping
    public ResponseEntity create(@RequestBody CreateRestaurantDTO data, UriComponentsBuilder uriBuilder) {
        var restaurant = service.save(new Restaurant(data));
        var uri = uriBuilder.path("/restaurant/{id}").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).body(restaurant);
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantListDTO>> getAll(@PageableDefault(sort = {"name"}, size = 5) Pageable pageable) {
        Page<Restaurant> restaurantPage = service.findAll(pageable);
        List<RestaurantListDTO> dtos = new ArrayList<>();
        restaurantPage.forEach(restaurant -> dtos.add(new RestaurantListDTO(restaurant)));
        return ResponseEntity.ok(new PageImpl<>(dtos, pageable, restaurantPage.getTotalElements()));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateRestaurantDTO data) {
        return ResponseEntity.ok(new RestaurantListDTO(service.update(new Restaurant(data), id)));
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(new RestaurantListDTO(service.findById(id)));
    }

    @PutMapping("/{id}/hours")
    public ResponseEntity addFunctioningHours(@PathVariable long id,@RequestBody FunctiotingHoursDTO data){
        var restaurant = service.findById(id);
        var functioningHours = new FunctioningHours(data);

        return ResponseEntity.ok(service.addFunctioningHours(restaurant, functioningHours));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deactivateById(@PathVariable Long id) {
        service.deactivateById(id);
        return ResponseEntity.noContent().build();
    }

}
