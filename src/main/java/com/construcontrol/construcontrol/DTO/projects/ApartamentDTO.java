package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.model.domain.projects.Construction;

public record ApartamentDTO(Construction construction, int number, double area, double price, boolean sold) {
}
