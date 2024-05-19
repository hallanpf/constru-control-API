package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.model.domain.projects.Construction;
import com.construcontrol.construcontrol.repositories.projects.ConstructionRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/constructions")
public class ConstructionController {
    @Autowired
    private ConstructionRepository constructionRepository;
    @Operation(summary = "Get all constructions", description = "Method that returns all constructions registered in the database", tags = {"constructions"})
    @GetMapping
    public ResponseEntity getAllConstructions() {
        var allConstructions = constructionRepository.findAll();
        return ResponseEntity.ok(allConstructions);
    }
    @Operation(summary = "Get construction by id", description = "Method that returns a construction registered in the database by id", tags = {"constructions"})
    @GetMapping("/{id}")
    public ResponseEntity getConstructionById(@PathVariable long id) {
        var construction = constructionRepository.getConstructionById(id);
        return ResponseEntity.ok(construction);
    }
    @Operation(summary = "Create a construction", description = "Method that creates a construction in the database", tags = {"constructions"})
    @PostMapping
    public ResponseEntity createConstruction(@RequestBody @Validated ConstructionDTO payload) {
        Construction constructions;
        try {
            constructions = new Construction(payload);
            constructionRepository.save(constructions);
            System.out.println(payload);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o empreendimento: " + e.getMessage());
        }
    }
    @Operation(summary = "Delete a construction", description = "Method that deletes a construction in the database", tags = {"constructions"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteConstructionById(@PathVariable long id) {
        try {
            constructionRepository.deleteConstructionById(id);
            return ResponseEntity.ok("Construção deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar dados da construção: " + e.getMessage());
        }
    }
    @Operation(summary = "Update a construction", description = "Method that updates a construction in the database", tags = {"constructions"})
    @PatchMapping("/{id}")
    public ResponseEntity updateConstructionById(@PathVariable long id, @RequestBody @Validated ConstructionDTO payload) {
        try {
            var construction = constructionRepository.getConstructionById(id);
            construction.update(payload);
            constructionRepository.save(construction);
            return ResponseEntity.ok("Construção atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar dados da construção: " + e.getMessage());
        }
    }
}
