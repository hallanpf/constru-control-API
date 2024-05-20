package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.EmployeeDTO;
import com.construcontrol.construcontrol.model.domain.projects.Employee;
import com.construcontrol.construcontrol.repositories.projects.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity getAllEmployees() {
        var allEmployees = employeeRepository.findAll();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable long id) {
        var employee = employeeRepository.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody @Validated EmployeeDTO payload) {
        Employee employees;
        try {
            employees = new Employee(payload);
            employeeRepository.save(employees);
            System.out.println(payload);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar funcionário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable long id) {
        try {
            employeeRepository.deleteEmployeeById(id);
            return ResponseEntity.ok("Funcionário deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateEmployeeById(@PathVariable long id, @RequestBody @Validated EmployeeDTO payload) {
        try {
            var employee = employeeRepository.getEmployeeById(id);
            employee.update(payload);
            employeeRepository.save(employee);
            return ResponseEntity.ok("Funcionário atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar dados do funcionário: " + e.getMessage());
        }
    }
}
