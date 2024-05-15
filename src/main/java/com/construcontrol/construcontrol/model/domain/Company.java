package com.construcontrol.construcontrol.model.domain;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "companies")
public class Company {
    @Id
    private long id;
    @Column(name = "company")
    private String company;
    @Column(name = "cnpj")
    private String cnpj;
    @OnetoOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
    @OnetoOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
      @OneToOne
    private Address address;
}
