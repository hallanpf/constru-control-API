package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;
import com.construcontrol.construcontrol.DTO.users.ManagerDTO;

import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Manager extends User {

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.GESTOR;

    public Manager(ManagerDTO ManagerDTO) {
        this.name = ManagerDTO.name();
        this.phone = ManagerDTO.phone();
        this.email = ManagerDTO.email();
        this.cpf = ManagerDTO.cpf();
        this.rg = ManagerDTO.rg();
        super.setUserRole(UserRole.GESTOR);
        this.address = ManagerDTO.address() != null ? new Address(ManagerDTO.address()) : null;

    }

    public void update(ManagerDTO payload) {
        this.name = payload.name();
        this.phone = payload.phone();
        this.email = payload.email();
        this.cpf = payload.cpf();
        this.rg = payload.rg();
        this.address = payload.address() != null ? new Address(payload.address()) : null;
    }

}
