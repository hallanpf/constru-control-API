package com.construcontrol.construcontrol.model.domain;

import lombok.*;
import jakarta.persistence.*;

@GetterS
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualAndHashCode(of = "id")
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private long id;
    @Column(name = "company")
    protected String company;
    @Column(name = "cnpj")
    protected String cnpj;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
