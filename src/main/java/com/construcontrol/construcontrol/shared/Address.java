package com.construcontrol.construcontrol.shared;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    @Column(name = "street_address", nullable = false)
    private String streetAddress;
    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;

    public Address (AddressDTO addressDTO){
        this.zipCode = addressDTO.zipCode();
        this.streetAddress = addressDTO.streetAddress();
        this.neighborhood = addressDTO.neighborhood();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
    }

}
