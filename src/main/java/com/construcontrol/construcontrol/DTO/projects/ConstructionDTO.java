package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;

import java.util.Date;

public record ConstructionDTO(String construction, String cnpj, Date startDate, Date endDate, double budget, double buildingLandArea, double buildingArea, double salesArea, int numberApartaments, CompanyDTO company, AddressDTO address) {
}
