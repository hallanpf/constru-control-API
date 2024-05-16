package com.construcontrol.construcontrol.controllers;

import com.construcontrol.construcontrol.DTO.CompanyDTO;
import com.construcontrol.construcontrol.model.domain.Company;
import com.construcontrol.construcontrol.repositories.CompanyRepository;
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

    @GetMapping
    public ResponseEntity getAllCompanies() {
        var allCompanies = companyRepository.findAll();
        return ResponseEntity.ok(allCompanies);
    }

    @PostMapping
    public ResponseEntity createCompany(@RequestBody @Validated CompanyDTO payload) {
        Company company;
        try {
            company = new Company(payload);
            companyRepository.save(company);
            System.out.println(payload);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o construtora: " + e.getMessage());
        }
    }
}
