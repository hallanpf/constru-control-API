package com.construcontrol.construcontrol.DTO.users;

import com.construcontrol.construcontrol.shared.AddressDTO;

public record UserDTO(String name, String phone, String userRole , String email, String password, String cpf, String rg, AddressDTO address) {
}
