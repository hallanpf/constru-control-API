package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.DTO.users.BrokerDTO;
import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;
import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Broker extends  User{
    private String creci;

    public Broker(BrokerDTO brokerDTO) {
        this.name = brokerDTO.name();
        this.phone = brokerDTO.phone();
        this.email = brokerDTO.email();
        this.cpf = brokerDTO.cpf();
        this.rg = brokerDTO.rg();
        this.creci = brokerDTO.creci();
        this.address = brokerDTO.address() != null ? new Address(brokerDTO.address()) : null;
        super.setUserRole(UserRole.CORRETOR);
    }


}
