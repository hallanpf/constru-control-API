package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.model.domain.projects.Company;
import com.construcontrol.construcontrol.repositories.projects.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

//    @Autowired
//    private ManagerRepository managerRepository;

    @GetMapping
    public ResponseEntity getAllCompanies() {
        var allCompanies = companyRepository.findAll();
        return ResponseEntity.ok(allCompanies);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompanyById(@PathVariable long id) {
        var company = companyRepository.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity createCompany(@RequestBody @Validated CompanyDTO payload) {
        Company companies;
        try {
            companies = new Company(payload);
            companyRepository.save(companies);
            System.out.println(payload);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar a construtora: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompanyById(@PathVariable long id) {
        try {
            companyRepository.deleteCompanyById(id);
            return ResponseEntity.ok("Construtora deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar contrutora: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateCompanyById(@PathVariable long id, @RequestBody @Validated CompanyDTO payload) {
        try {
            var company = companyRepository.getCompanyById(id);
            company.update(payload);
            companyRepository.save(company);
            return ResponseEntity.ok("Construtora atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar construtora: " + e.getMessage());
        }
    }

//    @GetMapping
//    public ResponseEntity getAllManager() {
//        var allManagers = managerRepository.findAll();
//        return ResponseEntity.ok(allManagers);
//    }
}
