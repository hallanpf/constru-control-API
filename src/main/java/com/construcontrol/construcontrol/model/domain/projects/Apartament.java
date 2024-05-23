package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.ApartamentDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "apartaments")
public class Apartament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "construction_id", nullable = false, referencedColumnName = "id")
    private Construction construction;
    @Column(name = "number")
    private int number;
    @Column(name = "area")
    private double area;
    @Column(name = "price")
    private double price;
    @Column(name = "sold_status")
    private boolean sold = false;
//    @Column(name="contract")
//    private Object contract;

    public Apartament(ApartamentDTO apartamentDTO) {
        this.construction = apartamentDTO.construction();
        this.number = apartamentDTO.number();
        this.area = apartamentDTO.area();
        this.price = apartamentDTO.price();
        this.sold = apartamentDTO.sold();
    }
}
