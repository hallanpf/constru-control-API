package com.construcontrol.construcontrol.DTO;

import com.construcontrol.construcontrol.shared.AddressDTO;

public record SupplierDTO(String supplier, String cnpj, String contact_name, String phone, String email, AddressDTO address) {
}
