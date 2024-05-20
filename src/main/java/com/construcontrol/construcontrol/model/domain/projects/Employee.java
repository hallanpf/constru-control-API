package com.construcontrol.construcontrol.model.domain.projects;

import com.construcontrol.construcontrol.DTO.projects.EmployeeDTO;
import com.construcontrol.construcontrol.model.domain.users.enums.Function;
import com.construcontrol.construcontrol.shared.Address;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "employee")
    private String employee;
    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(name = "phone")
    private String phone;
    @Enumerated(EnumType.STRING)
    private Function function;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "construction_id", referencedColumnName = "id")
//    private Construction construction;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public Employee(EmployeeDTO employeeDTO) {
        this.employee = employeeDTO.emplloyee();
        this.cpf = employeeDTO.cpf();
        this.phone = employeeDTO.phone();
        this.function = Function.valueOf(employeeDTO.function());
//        this.construction = findConstruction(employeeDTO.construction());
        this.address = employeeDTO.address() != null ? new Address(employeeDTO.address()) : null;
    }

    public void update(EmployeeDTO payload) {
        this.employee = payload.emplloyee();
        this.cpf = payload.cpf();
        this.phone = payload.phone();
        this.function = Function.valueOf(payload.function());
//        this.construction = findConstruction(payload.construction());
        this.address = payload.address() != null ? new Address(payload.address()) : null;
    }
}
