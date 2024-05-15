package com.construcontrol.construcontrol.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "clientes_cpf")
public final class Clients extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Column(name = "rg", unique = true, nullable = false)
    private String rg;
    @Column(name = "matrial_status", nullable = false)
    private MaritialStatus maritialStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;


}
