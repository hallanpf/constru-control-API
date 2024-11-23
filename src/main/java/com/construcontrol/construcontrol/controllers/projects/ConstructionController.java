package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.model.domain.projects.Construction;
import com.construcontrol.construcontrol.repositories.projects.ConstructionRepository;
import com.construcontrol.construcontrol.shared.Address;
import com.construcontrol.construcontrol.shared.utils.NullPropertyNamesUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/constructions")
@RestController
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
        var construction = constructionRepository.findById(id);
        return ResponseEntity.ok(construction);
    }

    @Operation(summary = "Create a construction", description = "Method that creates a construction in the database", tags = {"constructions"})
    @PostMapping
    public ResponseEntity createConstruction(@RequestBody @Validated ConstructionDTO constructionDTO) {
        Construction construction;
        try {
            construction = new Construction(constructionDTO);
            constructionRepository.save(construction);
            return ResponseEntity.ok(construction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o empreendimento: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete a construction", description = "Method that deletes a construction in the database", tags = {"constructions"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteConstruction(@PathVariable long id) {
        try {
            constructionRepository.deleteById(id);
            return ResponseEntity.ok("Construção deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar dados da construção: " + e.getMessage());
        }
    }

    @Operation(summary = "Update a construction", description = "Method that updates a construction in the database", tags = {"constructions"})
    @PatchMapping("/{id}")
    public Construction updateConstruction(@PathVariable long id, @RequestBody ConstructionDTO payload) {
        Construction existingConstruction = constructionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra não encontrada"));
        BeanUtils.copyProperties(payload, existingConstruction, NullPropertyNamesUtil.getNullPropertyNames(payload));
        if (payload.address() != null) {
            if (existingConstruction.getAddress() == null) {
                existingConstruction.setAddress(new Address(payload.address()));
            } else {
                existingConstruction.getAddress().update(payload.address());
            }
        }
        return constructionRepository.save(existingConstruction);
    }

//    @PatchMapping("/{id}")
//    public Construction onApartamentCreated(@PathVariable long id, @RequestBody ConstructionDTO payload) {
//        Construction existingConstruction = constructionRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Obra não encontrada"));
//        BeanUtils.copyProperties(payload, existingConstruction, NullPropertyNamesUtil.getNullPropertyNames(payload));
//        Apartament apartament = apartamentRepository.getReferenceById(id);
//        if (apartament.getIdConstruction() == id) {
//            setApartamentsList.add(apartament);
//        }
//        return constructionRepository.save(existingConstruction);
//    }

}
