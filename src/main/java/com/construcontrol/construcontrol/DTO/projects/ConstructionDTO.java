package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.model.domain.projects.Apartament;
import com.construcontrol.construcontrol.shared.AddressDTO;

import java.util.Date;
import java.util.List;

public record ConstructionDTO(String construction, String cnpj, Date startDate, Date endDate, double budget, double buildingLandArea, double buildingArea, double salesArea, int numberApartaments, List<Apartament> apartaments, AddressDTO address) {
// Quando resolver a questão do relacionamento incluir 'CompanyDTO company'
}
