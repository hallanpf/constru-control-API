package com.construcontrol.construcontrol.DTO.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompanyDTO {
    private long id;
    private String company;
    private String cnpj;
//    private AddressDTO address;
}