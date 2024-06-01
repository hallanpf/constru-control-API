package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.shared.Address;
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
    private String construction;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @Column(name = "buget")
    private double budget;
    @Column(name = "number_apartaments")
    private int numberApartaments;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "construction", cascade = CascadeType.ALL)
//    private List<Apartament> apartamentsList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Construction(ConstructionDTO constructionDTO) {
        this.construction = constructionDTO.construction();
        this.cnpj = constructionDTO.cnpj();
        this.startDate = constructionDTO.startDate();
        this.endDate = constructionDTO.endDate();
        this.budget = constructionDTO.budget();
//        this.apartamentsList = constructionDTO.apartamentsList();
        this.numberApartaments = constructionDTO.numberApartaments();
        this.address = constructionDTO.address() != null ? new Address(constructionDTO.address()) : null;
    }
}
