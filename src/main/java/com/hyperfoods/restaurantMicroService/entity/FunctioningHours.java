package com.hyperfoods.restaurantMicroService.entity;



import com.hyperfoods.restaurantMicroService.dtos.FunctiotingHoursDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tab_functioning_hours")
@Entity(name = "functioning_hours")
public class FunctioningHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "functioning_hours_id")
    private long id;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private LocalDateTime lastUpdated;
    @OneToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private Restaurant restaurant;

    public FunctioningHours(FunctiotingHoursDTO data) {
        this.openingTime = data.openingTime();
        this.closingTime = data.closingTime();
        this.lastUpdated = LocalDateTime.now();
    }
}
