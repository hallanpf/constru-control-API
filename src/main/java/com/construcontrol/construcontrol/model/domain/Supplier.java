package com.construcontrol.construcontrol.model.domain;

import com.construcontrol.construcontrol.DTO.SupplierDTO;
import com.construcontrol.construcontrol.shared.Address;
import com.construcontrol.construcontrol.shared.AddressDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;
    @Column(name = "contact_name")
    private String contact_name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "e-mail")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Supplier(SupplierDTO supplierDTO) {
        this.supplier = supplierDTO.supplier();
        this.cnpj = supplierDTO.cnpj();
        this.contact_name = supplierDTO.contact_name();
        this.phone = supplierDTO.phone();
        this.email = supplierDTO.email();
        this.address = createAddress(supplierDTO.address());
    }
    private Address createAddress(AddressDTO addressDTO) {
        if (addressDTO != null) {
            return new Address(addressDTO);
        } else {
            return null;
        }
    }
}

