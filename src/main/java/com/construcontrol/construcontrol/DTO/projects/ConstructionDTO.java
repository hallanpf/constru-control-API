package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;

public record ConstructionDTO(String construction, String cnpj, String startDate, String endDate, double budget, double buildingLandArea, double buildingArea, double salesArea, int numberApartaments, AddressDTO address) {
// Quando resolver a questão do relacionamento incluir 'CompanyDTO company'
}
