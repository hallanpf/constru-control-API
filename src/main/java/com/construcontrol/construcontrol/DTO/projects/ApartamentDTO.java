package com.construcontrol.construcontrol.DTO.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApartamentDTO {
    private long id;
    private ConstructionDTO construction;
    private String number;
    private double area;
    private double price;
    private boolean soldStatus;
    private long idClient;
}
