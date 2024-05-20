package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.shared.Address;
import com.construcontrol.construcontrol.shared.AddressDTO;
import jakarta.persistence.*;
import lombok.*;

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
    private String construction;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name="start_date")
    private String startDate;
    @Column(name="end_date")
    private String endDate;
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
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    private Company company;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Construction(ConstructionDTO constructionDTO) {
        this.construction = constructionDTO.construction();
        this.cnpj = constructionDTO.cnpj();
        this.startDate = constructionDTO.startDate();
        this.endDate = constructionDTO.endDate();
        this.budget = constructionDTO.budget();
        this.buildingLandArea = constructionDTO.buildingLandArea();
        this.buildingArea = constructionDTO.buildingArea();
        this.salesArea = constructionDTO.salesArea();
        this.numberApartaments = constructionDTO.numberApartaments();
        this.address = constructionDTO.address() != null ? new Address(constructionDTO.address()) : null;
    }



    public void update(ConstructionDTO payload) {
        this.construction = payload.construction();
        this.cnpj = payload.cnpj();
        this.startDate = payload.startDate();
        this.endDate = payload.endDate();
        this.budget = payload.budget();
        this.buildingLandArea = payload.buildingLandArea();
        this.buildingArea = payload.buildingArea();
        this.salesArea = payload.salesArea();
        this.numberApartaments = payload.numberApartaments();
        this.address = payload.address() != null ? new Address(payload.address()) : null;
    }

}
