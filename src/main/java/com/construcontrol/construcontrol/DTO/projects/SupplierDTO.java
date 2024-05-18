package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;

public record SupplierDTO(String supplier, String cnpj, String contact_name, String phone, String email, AddressDTO address) {
}
