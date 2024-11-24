package com.construcontrol.construcontrol.DTO.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressDTO {
    private String streetAddress;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

}
