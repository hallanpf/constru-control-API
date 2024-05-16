package com.construcontrol.construcontrol.DTO.users;


import com.construcontrol.construcontrol.shared.AddressDTO;

public record ClientsDTO(String name, String phone, String userType , String email, String cpf, String rg, String maritalStatus, AddressDTO address) {
}
