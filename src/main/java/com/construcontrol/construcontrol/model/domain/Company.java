package com.construcontrol.construcontrol.model.domain;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id

    private long id;
    @Column(name = "company")
    protected String company;
    @Column(name = "cnpj")
    protected String cnpj;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
      @OneToOne
    private Address address;
}
