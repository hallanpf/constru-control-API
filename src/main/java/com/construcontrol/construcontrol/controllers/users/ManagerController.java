package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.ManagerDTO;
import com.construcontrol.construcontrol.model.domain.users.Manager;
import com.construcontrol.construcontrol.repositories.users.ManagerRepository;
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

    @GetMapping
    public ResponseEntity getAllManager() {
        var allManagers = ManagerRepository.findAll();
        return ResponseEntity.ok(allManagers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getManagerById(@PathVariable long id) {
        var manager = ManagerRepository.getManagerById(id);
        return ResponseEntity.ok(manager);
    }

    @PostMapping
    public ResponseEntity createManager(@RequestBody @Validated ManagerDTO payload) {
        Manager managers;
        try {
            managers = new Manager(payload);
            ManagerRepository.save(managers);
            System.out.println(payload);
            return ResponseEntity.ok(payload);
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o gestor: " + e.getMessage());
        }
    }
}
