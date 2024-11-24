package com.construcontrol.construcontrol.DTO.users;

import com.construcontrol.construcontrol.model.domain.users.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ManagerDTO {
    private long id;
    private String name;
    private String cpf;
    private String rg;
    private String phone;
    private String email;
    private String password;
//    private AddressDTO address;
    private UserRole userRole;
}
