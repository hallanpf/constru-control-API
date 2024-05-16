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
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(name = "rg", unique = true, nullable = false, length = 9)
    private String rg;
   @Enumerated(EnumType.STRING)
    private MaritialStatus maritalStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

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


    }

