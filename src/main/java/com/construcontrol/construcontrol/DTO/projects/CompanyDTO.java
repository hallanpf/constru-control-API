package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;

public record CompanyDTO(String company, String cnpj, AddressDTO address) {
    // Quando consegui resolver a questão do relacionamento incluir 'ManagerDTO manager'
}
