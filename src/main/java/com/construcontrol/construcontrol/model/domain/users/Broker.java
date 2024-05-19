package com.construcontrol.construcontrol.model.domain.users;

import com.construcontrol.construcontrol.DTO.users.BrokerDTO;
import com.construcontrol.construcontrol.model.domain.users.enums.UserType;
import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "broker")
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
        super.setUserType(UserType.CORRETOR);
    }


}
