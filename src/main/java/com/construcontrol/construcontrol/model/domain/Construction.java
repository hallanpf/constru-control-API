package com.construcontrol.construcontrol.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "constructions")
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "construction")
    protected String company;
    @Column(name = "cnpj")
    protected String cnpj;
    @Column(name="start_date")
    protected Date startDate;
    @Column(name="end_date")
    protected Date endDate;
    @Column(name = "buget")
    protected double budget;
    @Column(name = "building_land_area")
    protected double buildingLandArea;
    @Column(name = "building_area")
    protected double buildingArea;
    @Column(name = "sales_area")
    protected double salesArea;
    @Column(name = "number_apartaments")
    protected int numberApartaments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
