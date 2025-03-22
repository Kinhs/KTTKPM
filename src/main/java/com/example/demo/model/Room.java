package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column
    private Double area;

    @Column(nullable = false)
    private Double rentAmount;

    @Column
    private String description;

    @Column
    private Boolean isOccupied = false;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Asset> assets;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @OneToMany(mappedBy = "room")
    private List<Customer> customers;
}