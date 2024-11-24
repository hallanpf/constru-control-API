package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.model.domain.projects.Company;
import com.construcontrol.construcontrol.repositories.projects.CompanyRepository;
import com.construcontrol.construcontrol.model.domain.projects.Address;
import com.construcontrol.construcontrol.shared.utils.NullPropertyNamesUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/companies")
@RestController
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @Operation(summary = "Get all companies", description = "Method that returns all companies registered in the database", tags = {"companies"})
    @GetMapping
    public ResponseEntity getAllCompanies() {
        var allCompanies = companyRepository.findAll();
        return ResponseEntity.ok(allCompanies);
    }

    @Operation(summary = "Get company by id", description = "Method that returns a company registered in the database by id", tags = {"companies"})
    @GetMapping("/{id}")
    public ResponseEntity getCompanyById(@PathVariable long id) {
        var company = companyRepository.findById(id);
        return ResponseEntity.ok(company);
    }

    @Operation( summary = "Create a company", description = "Method that creates a company in the database", tags = {"companies"})
    @PostMapping
    public ResponseEntity createCompany(@RequestBody @Validated CompanyDTO companyDTO) {
        Company company;
        try {
            company = new Company(companyDTO);
            companyRepository.save(company);
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar a construtora: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete a company", description = "Method that deletes a company in the database", tags = {"companies"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompany(@PathVariable long id) {
        try {
            companyRepository.deleteById(id);
            return ResponseEntity.ok("Construtora deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar a contrutora: " + e.getMessage());
        }
    }

    @Operation(summary = "Update a company", description = "Method that updates a company in the database", tags = {"companies"})
    @PatchMapping("/{id}")
    // Método PATCH que recebe um ID na URL para identificar a construtora a ser atualizado
    public Company updateCompany(@PathVariable long id, @RequestBody CompanyDTO payload) {
        // Obtém a construtora existente pelo ID fornecido
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Construtora não encontrada"));
        // Copia as propriedades do payload para a construtora existente, exceto as propriedades nulas
        BeanUtils.copyProperties(payload, existingCompany, NullPropertyNamesUtil.getNullPropertyNames(payload));
        if (payload.address() != null) { // Verifica se o endereço foi enviado no payload
            if (existingCompany.getAddress() == null) {// Verifica se a construtora já possui um endereço
                // Se não possuir, cria um novo endereço com base nos dados do payload
                existingCompany.setAddress(new Address(payload.address()));
            } else {
                // Se já possuir, atualiza o endereço existente com os dados do payload
                existingCompany.getAddress().update(payload.address());
            }
        }
        // Salva as alterações no banco de dados e retorna a construtora atualizada
        return companyRepository.save(existingCompany);
    }

}
