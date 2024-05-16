package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;
import com.construcontrol.construcontrol.DTO.users.ManagerDTO;

public record CompanyDTO(String company, String cnpj, ManagerDTO manager, AddressDTO address) {
}
