package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.model.domain.projects.Construction;
import com.construcontrol.construcontrol.repositories.ConstructionRepository;
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

    @GetMapping
    public ResponseEntity getAllConstructions() {
        var allConstructions = constructionRepository.findAll();
        return ResponseEntity.ok(allConstructions);
    }

    @PostMapping
    public ResponseEntity createConstruction(@RequestBody @Validated ConstructionDTO payload) {
        Construction construction;
        try {
            construction = new Construction(payload);
            constructionRepository.save(construction);
            System.out.println(payload);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o empreendimento: " + e.getMessage());
        }
    }
}
