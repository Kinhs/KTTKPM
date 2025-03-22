package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private YearMonth billMonth;

    @Column(nullable = false)
    private LocalDate billDate;

    @Column(nullable = false)
    private Double rentAmount;

    @Column
    private Integer previousElectricReading;

    @Column
    private Integer currentElectricReading;

    @Column
    private Double electricPrice;

    @Column
    private Double electricAmount;

    @Column
    private Integer previousWaterReading;

    @Column
    private Integer currentWaterReading;

    @Column
    private Double waterPrice;

    @Column
    private Double waterAmount;

    @Column
    private Double otherAmount;

    @Column
    private String otherDescription;

    @Column(nullable = false)
    private Double totalAmount;

    @Column
    private Boolean isPaid = false;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne(mappedBy = "bill")
    private Payment payment;
}
