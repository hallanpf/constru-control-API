package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.shared.AddressDTO;
import com.construcontrol.construcontrol.DTO.users.ManagerDTO;
import com.construcontrol.construcontrol.shared.Address;
import com.construcontrol.construcontrol.model.domain.users.Manager;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Company(CompanyDTO companyDTO) {
        this.company = companyDTO.company();
        this.cnpj = companyDTO.cnpj();
        this.manager = createManager(companyDTO.manager());
        this.address = createAddress(companyDTO.address());
    }

    private Address createAddress(AddressDTO addressDTO) {
        if (addressDTO != null) {
            return new Address(addressDTO);
        } else {
            return null;
        }
    }

    private Manager createManager(ManagerDTO managerDTO) {
        if (managerDTO != null) {
            return new Manager(managerDTO);
        } else {
            return null;
        }
    }
}
