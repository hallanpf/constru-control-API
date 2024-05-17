package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.UserType;
import com.construcontrol.construcontrol.shared.AddressDTO;
import com.construcontrol.construcontrol.DTO.users.ManagerDTO;

import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("ALL")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manager")

public class Manager extends User {

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.GESTOR;

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

    public void update(ManagerDTO payload) {
        this.name = payload.name();
        this.phone = payload.phone();
        this.email = payload.email();
        this.cpf = payload.cpf();
        this.rg = payload.rg();
        this.address = createAddress(payload.address());
    }
}
