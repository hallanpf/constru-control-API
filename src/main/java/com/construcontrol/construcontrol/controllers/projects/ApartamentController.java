package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.ApartamentDTO;
import com.construcontrol.construcontrol.model.domain.projects.Apartament;
import com.construcontrol.construcontrol.repositories.projects.ApartamentRepository;
import com.construcontrol.construcontrol.shared.utils.NullPropertyNamesUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apartaments")
public class ApartamentController {
    @Autowired
    private ApartamentRepository apartamentRepository;

    @Operation(summary = "Get all apartaments", description = "Method that returns all apartaments registered in the database", tags = {"apartaments"})
    @GetMapping
    public ResponseEntity getAllApartaments() {
        var allApartaments = apartamentRepository.findAll();
        return ResponseEntity.ok(allApartaments);
    }

    @Operation(summary = "Get apartaments by id", description = "Method that returns an apartament registered in the database by id", tags = {"apartaments"})
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        var apartament = apartamentRepository.getApartamentById(id);
        return ResponseEntity.ok(apartament);
    }

    @Operation( summary = "Create a apartament", description = "Method that creates an apartament in the database", tags = {"apartaments"})
    @PostMapping
    public ResponseEntity createCompany(@RequestBody @Validated ApartamentDTO apartamentDTO) {
        Apartament apartament;
        try {
            apartament = new Apartament(apartamentDTO);
            apartamentRepository.save(apartament);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar apartamento: " + e.getMessage());
        }
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
        BeanUtils.copyProperties(payload, existingApartament, NullPropertyNamesUtil.getNullPropertyNames(payload));
        return apartamentRepository.save(existingApartament);
    }
}
