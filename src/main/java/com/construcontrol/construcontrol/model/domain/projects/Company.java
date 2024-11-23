package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "company")
    private String company;
    @Column(name = "cnpj", unique = true, nullable = false, length = 14)
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Company(CompanyDTO companyDTO) {
        this.company = companyDTO.company();
        this.cnpj = companyDTO.cnpj();
        this.address = companyDTO.address() != null ? new Address(companyDTO.address()) : null;
    }

}
