package com.construcontrol.construcontrol.controllers.projects;

import com.construcontrol.construcontrol.DTO.projects.AddressDTO;
import com.construcontrol.construcontrol.services.projects.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/address")
@RequiredArgsConstructor
@RestController
public class AddressController {
    private final ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressDTO> buscarEndereco(@PathVariable String cep) {
        AddressDTO endereco = viaCepService.findAddress(cep);
        return ResponseEntity.ok(endereco);
    }
}
