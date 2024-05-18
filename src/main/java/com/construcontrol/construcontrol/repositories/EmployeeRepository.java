package com.construcontrol.construcontrol.repositories;

import com.construcontrol.construcontrol.model.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getEmployeeById(long id);
    Optional<Employee> getEmployeeByCpf(String cpf);

    Employee deleteEmployeeById(Long id);
    Optional<Employee> deleteEmployeeByCpf(String cpf);
}
