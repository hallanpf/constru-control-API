package com.construcontrol.construcontrol.DTO.projects;

import com.construcontrol.construcontrol.shared.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConstructionDTO {
    private long id;
    private String construction;
    private String cnpj;
    private Date startDate;
    private Date endDate;
    private CompanyDTO company;
    private AddressDTO address;
}