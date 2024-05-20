package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;

public record EmployeeDTO(String emplloyee, String cpf, String phone, String function, AddressDTO address) {
//Quando resolver a questão do relacionamento, incluir 'CompanyDTO company, ConstructionDTO construction'
}
