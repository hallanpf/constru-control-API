package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.ManagerDTO;
import com.construcontrol.construcontrol.services.users.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/manager")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ManagerController {
    private final ManagerService managerService;

    @Operation(summary = "Create a manager", description = "Method that creates a manager in the database", tags = {"managers"})
    @PostMapping
    public ResponseEntity<ManagerDTO> criarManager(@RequestBody @Validated ManagerDTO managerDTO) {
        log.info("Chamando criarManager no ManagerController com dados: {}", managerDTO);
        ManagerDTO newManager = managerService.criarManager(managerDTO);
        return ResponseEntity.ok(newManager);
    }

    @Operation(summary = "Get all managers", description = "Method that returns all managers registered in the database", tags = {"managers"})
    @GetMapping
    public ResponseEntity<List<ManagerDTO>> listarManagers() {
        log.info("Chamando listarManagers no ManagerController");
        List<ManagerDTO> managers = managerService.listarManagers();
        return ResponseEntity.ok(managers);
    }

    @Operation(summary = "Get managers by id", description = "Method that returns a manager registered in the database by id", tags = {"managers"})
    @GetMapping("/{id}")
    public ResponseEntity<ManagerDTO> buscarManagerPorId(@PathVariable long id) {
        log.info("Chamando buscarManagerPorId no ManagerController com id: {}", id);
        Optional<ManagerDTO> manager = managerService.buscarManagerPorId(id);
        return manager.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a manager", description = "Method that updates a manager in the database", tags = {"managers"})
    @PatchMapping("/{id}")
    public ResponseEntity<ManagerDTO> atualizarManager(@PathVariable long id, @RequestBody ManagerDTO managerDTO) {
        log.info("Chamando atualizarManager no ManagerController com id: {} e dados: {}", id, managerDTO);
        Optional<ManagerDTO> managerAtualizado = managerService.atualizarManager(id, managerDTO);
        return managerAtualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an manager", description = "Method that deletes an manager in the database", tags = {"managers"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarManager(@PathVariable long id) {
        log.info("Chamando deletarManager no ManagerController com id: {}", id);
        boolean deletado = managerService.deletarManager(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
