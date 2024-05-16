package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.UserType;
import com.construcontrol.construcontrol.shared.AddressDTO;
import com.construcontrol.construcontrol.DTO.users.ManagerDTO;
import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "manager")

public class Manager extends User {

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Column(name = "rg", unique = true, nullable = false)
    private String rg;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    public Manager(ManagerDTO ManagerDTO) {
        this.name = ManagerDTO.name();
        this.phone = ManagerDTO.phone();
        this.email = ManagerDTO.email();
        this.cpf = ManagerDTO.cpf();
        this.rg = ManagerDTO.rg();
        super.setUserType(UserType.GESTOR);
        this.address = createAddress(ManagerDTO.address());

    }
    private Address createAddress(AddressDTO addressDTO) {
        if (addressDTO != null) {
            return new Address(addressDTO);
        } else {
            return null;
        }
    }

}
