package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.model.domain.users.enums.MaritialStatus;
import com.construcontrol.construcontrol.model.domain.users.enums.UserType;
import com.construcontrol.construcontrol.shared.AddressDTO;
import com.construcontrol.construcontrol.DTO.users.ClientsDTO;

import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@SuppressWarnings("ALL")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "clientes_cpf")
public class Clients extends User {

   @Enumerated(EnumType.STRING)
    private MaritialStatus maritalStatus;
    public Clients(ClientsDTO clientsDTO) {
        this.name = clientsDTO.name();
        this.phone = clientsDTO.phone();
        this.email = clientsDTO.email();
        this.cpf = clientsDTO.cpf();
        this.rg = clientsDTO.rg();
        this.maritalStatus = MaritialStatus.valueOf(clientsDTO.maritalStatus());
        super.setUserType(UserType.CLIENTE);
        this.address = createAddress(clientsDTO.address());
    }
    private Address createAddress(AddressDTO addressDTO) {
        if (addressDTO != null) {
            return new Address(addressDTO);
        } else {
            return null;
        }
    }

    public void update(ClientsDTO payload) {
        this.name = payload.name();
        this.phone = payload.phone();
        this.email = payload.email();
        this.cpf = payload.cpf();
        this.rg = payload.rg();
        this.maritalStatus = MaritialStatus.valueOf(payload.maritalStatus());
        this.address = createAddress(payload.address());
    }

}
