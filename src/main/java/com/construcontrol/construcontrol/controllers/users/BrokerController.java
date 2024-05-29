package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.BrokerDTO;
import com.construcontrol.construcontrol.model.domain.users.Broker;
import com.construcontrol.construcontrol.repositories.users.BrokerRepository;
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
@RequestMapping("/brokers")
public class BrokerController {
    @Autowired
    private BrokerRepository brokerRepository;

    @Operation(summary = "Get all brokers", description = "Method that returns all brokers registered in the database", tags = {"brokers"})
    @GetMapping
    public ResponseEntity getAllBrokers() {
        var allBrokers = brokerRepository.findAll();
        return ResponseEntity.ok(allBrokers);
    }
    @Operation(summary = "Get broker by id", description = "Method that returns a broker registered in the database by id", tags = {"brokers"})
    @GetMapping("/{id}")
    public ResponseEntity getBrokerById(@PathVariable long id) {
        var broker = brokerRepository.getBrokerById(id);
        return ResponseEntity.ok(broker);
    }
    @Operation(summary = "Create a broker", description = "Method that creates a broker in the database", tags = {"brokers"})
    @PostMapping
    public ResponseEntity createBroker(@RequestBody @Validated BrokerDTO brokerDTO) {
        Broker broker;
        try {
            broker = new Broker(brokerDTO);
            brokerRepository.save(broker);
            return ResponseEntity.ok(broker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o corretor: " + e.getMessage());
        }
    }
    @Operation(summary = "Delete a broker", description = "Method that deletes a broker in the database", tags = {"brokers"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBroker(@PathVariable long id) {
        try {
            brokerRepository.deleteById(id);
            return ResponseEntity.ok("Corretor deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o corretor: " + e.getMessage());
        }
    }
    @Operation(summary = "Update a broker", description = "Method that updates a broker in the database", tags = {"brokers"})
    @PatchMapping("/{id}")
    // Método PATCH que recebe um ID na URL para identificar o corretor a ser atualizado
    public Broker updateBroker(@PathVariable long id, @RequestBody BrokerDTO payload) {
        // Obtém o corretor existente pelo ID fornecido
        Broker existingBroker = brokerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corretor não encontrado"));

        // Copia as propriedades do payload para o corretor existente, exceto as propriedades nulas
        BeanUtils.copyProperties(payload, existingBroker, NullPropertyNamesUtil.getNullPropertyNames(payload));
        if (payload.address() != null) { // Verifica se o endereço foi enviado no payload
            if (existingBroker.getAddress() == null) {// Verifica se o corretor já possui um endereço
                // Se não possuir, cria um novo endereço com base nos dados do payload
                existingBroker.setAddress(new Address(payload.address()));
            } else {
                // Se já possuir, atualiza o endereço existente com os dados do payload
                existingBroker.getAddress().update(payload.address());
            }
        }

        // Salva as alterações no banco de dados e retorna o corretor atualizado
        return brokerRepository.save(existingBroker);

    }


}




