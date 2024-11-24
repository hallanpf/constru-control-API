package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.CompanyDTO;
import com.construcontrol.construcontrol.services.projects.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/companies")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    @Operation( summary = "Create a company", description = "Method that creates a company in the database", tags = {"companies"})
    @PostMapping
    public ResponseEntity<CompanyDTO> criarCompany(@RequestBody @Validated CompanyDTO companyDTO) {
        log.info("Chamando criarCompany no CompanyController com dados: {}", companyDTO);
        CompanyDTO newCompany = companyService.criarCompany(companyDTO);
        return ResponseEntity.ok(newCompany);
    }

    @Operation(summary = "Get all companies", description = "Method that returns all companies registered in the database", tags = {"companies"})
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> listarCompanies() {
        log.info("Chamando listarCompanys no CompanyController");
        List<CompanyDTO> companies = companyService.listarCompanies();
        return ResponseEntity.ok(companies);
    }

    @Operation(summary = "Get company by id", description = "Method that returns a company registered in the database by id", tags = {"companies"})
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> buscarCompanyPorId(@PathVariable long id) {
        log.info("Chamando buscarCompanyPorId no CompanyController com id: {}", id);
        Optional<CompanyDTO> company = companyService.buscarCompanyPorId(id);
        return company.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Update a company", description = "Method that updates a company in the database", tags = {"companies"})
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDTO> atualizarCompany(@PathVariable long id, @RequestBody CompanyDTO companyDTO) {
        log.info("Chamando atualizarCompany no CompanyController com id: {} e dados: {}", id, companyDTO);
        Optional<CompanyDTO> companyAtualizado = companyService.atualizarCompany(id, companyDTO);
        return companyAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a company", description = "Method that deletes a company in the database", tags = {"companies"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompany(@PathVariable long id) {
        log.info("Chamando deletarCompany no CompanyController com id: {}", id);
        boolean deletado = companyService.deletarCompany(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
