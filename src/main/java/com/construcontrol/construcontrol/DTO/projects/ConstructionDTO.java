package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;

import java.util.Date;

public record ConstructionDTO(String construction, String cnpj, Date startDate, Date endDate, double budget, int numberApartaments, AddressDTO address) {
//, List<Apartament> apartamentsList
}
