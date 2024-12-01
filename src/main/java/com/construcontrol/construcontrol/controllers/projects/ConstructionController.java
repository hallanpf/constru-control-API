package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.ConstructionDTO;
import com.construcontrol.construcontrol.services.projects.ConstructionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/constructions")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ConstructionController {
    private final ConstructionService constructionService;

    @Operation(summary = "Create a construction", description = "Method that creates a construction in the database", tags = {"constructions"})
    @PostMapping
    public ResponseEntity<ConstructionDTO> criarConstruction(@RequestBody @Validated ConstructionDTO constructionDTO) {
        log.info("Chamando criarConstruction no ConstructionController com dados: {}", constructionDTO);
        ConstructionDTO newConstruction = constructionService.criarConstruction(constructionDTO);
        return ResponseEntity.ok(newConstruction);
    }

    @Operation(summary = "Get all constructions", description = "Method that returns all constructions registered in the database", tags = {"constructions"})
    @GetMapping
    public ResponseEntity<List<ConstructionDTO>> listarConstructions() {
        log.info("Chamando listarConstructions no ConstructionController");
        List<ConstructionDTO> constructions = constructionService.listarConstructions();
        return ResponseEntity.ok(constructions);
    }

    @Operation(summary = "Get constructions by id", description = "Method that returns a construction registered in the database by id", tags = {"constructions"})
    @GetMapping("/{id}")
    public ResponseEntity<ConstructionDTO> buscarConstructionPorId(@PathVariable long id) {
        log.info("Chamando buscarConstructionPorId no ConstructionController com id: {}", id);
        Optional<ConstructionDTO> construction = constructionService.buscarConstructionPorId(id);
        return construction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a construction", description = "Method that updates a construction in the database", tags = {"constructions"})
    @PatchMapping("/{id}")
    public ResponseEntity<ConstructionDTO> atualizarConstruction(@PathVariable long id, @RequestBody ConstructionDTO constructionDTO) {
        log.info("Chamando atualizarConstruction no ConstructionController com id: {} e dados: {}", id, constructionDTO);
        Optional<ConstructionDTO> constructionAtualizado = constructionService.atualizarConstruction(id, constructionDTO);
        return constructionAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an construction", description = "Method that deletes an construction in the database", tags = {"constructions"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConstruction(@PathVariable long id) {
        log.info("Chamando deletarConstruction no ConstructionController com id: {}", id);
        boolean deletado = constructionService.deletarConstruction(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
