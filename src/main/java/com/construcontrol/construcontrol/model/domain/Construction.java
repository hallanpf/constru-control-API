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
    private String company;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @Column(name = "buget")
    private double budget;
    @Column(name = "building_land_area")
    private double buildingLandArea;
    @Column(name = "building_area")
    private double buildingArea;
    @Column(name = "sales_area")
    private double salesArea;
    @Column(name = "number_apartaments")
    private int numberApartaments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
