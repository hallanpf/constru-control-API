package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.SupplierDTO;
import com.construcontrol.construcontrol.model.domain.projects.Supplier;
import com.construcontrol.construcontrol.model.domain.users.Clients;
import com.construcontrol.construcontrol.repositories.projects.SupplierRepository;
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
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public ResponseEntity getAllSuppliers() {
        var allSuppliers = supplierRepository.findAll();
        return ResponseEntity.ok(allSuppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSupplierById(@PathVariable long id) {
        var supplier = supplierRepository.getSupplierById(id);
        return ResponseEntity.ok(supplier);
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSupplierById(@PathVariable long id) {
        try {
            supplierRepository.deleteById(id);
            return ResponseEntity.ok("Fornecedor deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar fornecedor: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Supplier updateSupplier(@PathVariable long id, @RequestBody @Validated SupplierDTO payload) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        BeanUtils.copyProperties(payload, existingSupplier, NullPropertyNamesUtil.getNullPropertyNames(payload));
        if (payload.address() != null) {
            if (existingSupplier.getAddress() == null) {
                existingSupplier.setAddress(new Address(payload.address()));
            } else {
                existingSupplier.getAddress().update(payload.address());
            }
        }
        return supplierRepository.save(existingSupplier);
}
}
