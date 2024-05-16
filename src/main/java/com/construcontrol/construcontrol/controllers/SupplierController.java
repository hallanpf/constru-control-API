package com.construcontrol.construcontrol.controllers;

import com.construcontrol.construcontrol.DTO.SupplierDTO;
import com.construcontrol.construcontrol.model.domain.Supplier;
import com.construcontrol.construcontrol.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public ResponseEntity getAllSuppliers() {
        var allSuppliers = supplierRepository.findAll();
        return ResponseEntity.ok(allSuppliers);
    }

    @PostMapping
    public ResponseEntity createSupplier(@RequestBody @Validated SupplierDTO payload) {
        Supplier supplier;
        try {
            supplier = new Supplier(payload);
            supplierRepository.save(supplier);
            System.out.println(payload);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar fornecedor: " + e.getMessage());
        }
    }
}
