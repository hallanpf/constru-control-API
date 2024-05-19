package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
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
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "company")
    private String company;
    @Column(name = "cnpj", unique = true, nullable = false, length = 14)
    private String cnpj;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "manager_id", nullable = false, referencedColumnName = "id")
//    private Manager manager;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Company(CompanyDTO companyDTO) {
        this.company = companyDTO.company();
        this.cnpj = companyDTO.cnpj();
//        this.manager = getAllManager(managerDTO.name());
        this.address = companyDTO.address() != null ? new Address(companyDTO.address()) : null;
    }



    public void update(CompanyDTO payload) {
        this.company = payload.company();
        this.cnpj = payload.cnpj();
//        this.manager = payload.manager();
        this.address = payload.address() != null ? new Address(payload.address()) : null;
    }
}
