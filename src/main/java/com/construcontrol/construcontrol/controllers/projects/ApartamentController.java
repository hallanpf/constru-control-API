package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.ApartamentDTO;
import com.construcontrol.construcontrol.model.domain.projects.Apartament;
import com.construcontrol.construcontrol.model.domain.projects.Construction;
import com.construcontrol.construcontrol.repositories.projects.ApartamentRepository;
import com.construcontrol.construcontrol.repositories.projects.ConstructionRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartaments")
public class ApartamentController {
    @Autowired
    private ApartamentRepository apartamentRepository;

    @Autowired
    private ConstructionRepository constructionRepository;

    @Operation(summary = "Get all apartaments", description = "Method that returns all apartaments registered in the database", tags = {"apartaments"})
    @GetMapping
    public ResponseEntity getAllApartaments() {
        var allApartaments = apartamentRepository.findAll();
        return ResponseEntity.ok(allApartaments);
    }

//    @Operation(summary = "Get apartaments by id", description = "Method that returns an apartament registered in the database by id", tags = {"apartaments"})
//    @GetMapping("/{id}")
//    public ResponseEntity findById(@PathVariable long id) {
//        var apartament = apartamentRepository.getApartamentById(id);
//        return ResponseEntity.ok(apartament);
//    }

    @Operation(summary = "Get apartament sales table by construction id", description = "Method that returns an apartment sales table registered in the database by id", tags = {"apartaments"})
    @GetMapping("/{idConstruction}")
    public ResponseEntity apartamentsSalesTable(@PathVariable int idConstruction) {
        List<Apartament> apartamentList = apartamentRepository.findByIdConstruction(idConstruction);
        Construction construction = constructionRepository.getConstructionById(idConstruction);
        String tabelaPreco = "============= TABELA DE PREÇO =============\nEmpreendimento: " + construction.getConstruction();
        for (Apartament apartament : apartamentList) {
            tabelaPreco += ("\nApto " + apartament.getNumber() + " - " + apartament.getArea() + "m² - R$" + apartament.getPrice() + " - " + ((apartament.isSoldStatus() == false) ? "Disponível" : "Vendido"));
        }
        return ResponseEntity.ok(tabelaPreco);
    }

    @Operation(summary = "Create an apartament", description = "Method that creates an apartament in the database", tags = {"apartaments"})
    @PostMapping
    public ResponseEntity createApartament(@RequestBody @Validated ApartamentDTO apartamentDTO) {
        Apartament apartament;
        try {
            apartament = new Apartament(apartamentDTO);
            // find de pesquisa de apartamento existente
            apartamentRepository.save(apartament);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(apartamentDTO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar apartamento: " + e.getMessage());
        } // catch de exceção | cheked e uncheked exception
    }

    @Operation(summary = "Delete an apartament", description = "Method that deletes an apartament in the database", tags = {"apartaments"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteApartament(@PathVariable long id) {
        try {
            apartamentRepository.deleteById(id);
            return ResponseEntity.ok("Apartamento deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar apartamento: " + e.getMessage());
        }
    }

    @Operation(summary = "Update an apartament", description = "Method that updates an apartament in the database", tags = {"apartaments"})
    @PatchMapping("/{id}")
    public Apartament updateApartament(@PathVariable long id, @RequestBody ApartamentDTO payload) {
        Apartament existingApartament = apartamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));
        if (payload.idConstruction() != 0) {
            existingApartament.setIdConstruction(payload.idConstruction());
        }
        if (payload.number() != null) {
            existingApartament.setNumber(payload.number());
        }
        if (payload.area() != 0) {
            existingApartament.setArea(payload.area());
        }
        if (payload.price() != 0) {
            existingApartament.setPrice(payload.price());
        }
        if (payload.idClient() != 0) {
            existingApartament.setIdClient(payload.idClient());
        }
        if (existingApartament.getIdClient() != 0) {
            existingApartament.setSoldStatus(true);
        }
        return apartamentRepository.save(existingApartament);
    }

}
