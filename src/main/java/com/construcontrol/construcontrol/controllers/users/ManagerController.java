package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.ManagerDTO;
import com.construcontrol.construcontrol.model.domain.users.Manager;
import com.construcontrol.construcontrol.repositories.users.ManagerRepository;
import com.construcontrol.construcontrol.shared.Address;
import com.construcontrol.construcontrol.shared.utils.NullPropertyNamesUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerRepository ManagerRepository;

    @Operation(summary = "Get all managers", description = "Method that returns all managers registered in the database", tags = {"managers"})
    @GetMapping
    public ResponseEntity getAllManager() {
        var allManagers = ManagerRepository.findAll();
        return ResponseEntity.ok(allManagers);
    }

    @Operation(summary = "Get manager by id", description = "Method that returns a manager registered in the database by id", tags = {"managers"})
    @GetMapping("/{id}")
    public ResponseEntity getManagerById(@PathVariable long id) {
        var manager = ManagerRepository.getManagerById(id);
        return ResponseEntity.ok(manager);
    }

    @Operation(summary = "Create a manager", description = "Method that creates a manager in the database", tags = {"managers"})
    @PostMapping
    public ResponseEntity createManager(@RequestBody @Validated ManagerDTO payload) {
        Manager managers;
        try {
            managers = new Manager(payload);
            ManagerRepository.save(managers);
            return ResponseEntity.ok(managers);
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o gestor: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete a manager", description = "Method that deletes a manager in the database", tags = {"managers"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteManagerById(@PathVariable long id) {
        try {
            ManagerRepository.deleteById(id);
            return ResponseEntity.ok("Gestor deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o gestor: " + e.getMessage());
        }
    }

    @Operation(summary = "Update a manager", description = "Method that updates a manager in the database", tags = {"managers"})
    @PatchMapping("/{id}")
    public Manager updateManager(@PathVariable long id, @RequestBody ManagerDTO payload) {
        Manager existingManager = ManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));
        BeanUtils.copyProperties(payload, existingManager, NullPropertyNamesUtil.getNullPropertyNames(payload));
        if (payload.address() != null) {
            if (existingManager.getAddress() == null) {
                existingManager.setAddress(new Address(payload.address()));
            } else {
                existingManager.getAddress().update(payload.address());
            }
        }
        return ManagerRepository.save(existingManager);
    }

}

