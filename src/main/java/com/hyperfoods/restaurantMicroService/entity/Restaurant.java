package com.hyperfoods.restaurantMicroService.entity;


import com.hyperfoods.restaurantMicroService.dtos.CreateRestaurantDTO;
import com.hyperfoods.restaurantMicroService.dtos.UpdateRestaurantDTO;
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
@Table(name = "tab_restaurant")
@Entity(name = "restaurant")
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_id")
    private long id;
    private String name;
    private String description;
    @Column(name = "is_active")
    private boolean active = true;
    @JoinColumn(name = "functioning_hours", referencedColumnName = "functioning_hours_id")
    @OneToOne(cascade = CascadeType.ALL)
    private FunctioningHours functioningHours;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    public Restaurant(CreateRestaurantDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.active = true;
        this.created = LocalDateTime.now();
        this.address = new Address(data.address());
    }

    public Restaurant(UpdateRestaurantDTO data) {
        this.name = data.name();
        this.description = data.description();
    }


}
