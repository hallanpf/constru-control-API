package com.construcontrol.construcontrol.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@GetterS
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualAndHashCode(of = "id")
@Entity
@Table(name = "constructions")
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private long id;
    @Column(name = "construction")
    protected String company;
    @Column(name = "cnpj")
    protected String cnpj;
    @Column(name="start date")
    protected Date startDate;
    @Column(name="end date")
    protected Date endDate;
    @Column(name = "buget")
    protected double budget;
    @Column(name = "building land area")
    protected double buildingLandArea;
    @Column(name = "building area")
    protected double buildingArea;
    @Column(name = "sales area")
    protected double salesArea;
    @Column(name = "number of apartaments")
    protected int numberApartaments;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
