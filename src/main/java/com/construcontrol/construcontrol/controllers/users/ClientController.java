package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.ClientsDTO;
import com.construcontrol.construcontrol.model.domain.users.Clients;
import com.construcontrol.construcontrol.repositories.users.ClientRepository;
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
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientReposirtory;

    @Operation(summary = "Get all clients", description = "Method that returns all clients registered in the database", tags = {"clients"})
    @GetMapping
    public ResponseEntity getAllClients() {
        var allClients = clientReposirtory.findAll();
        return ResponseEntity.ok(allClients);
    }
    @Operation(summary = "Get client by id", description = "Method that returns a client registered in the database by id", tags = {"clients"})
    @GetMapping("/{id}")
    public ResponseEntity getClientById(@PathVariable long id) {
        var client = clientReposirtory.getClientsById(id);
        System.out.println(client);
        return ResponseEntity.ok(client);
    }
    @Operation(summary = "Delete a client", description = "Method that deletes a client in the database", tags = {"clients"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable long id) {
        try {
            clientReposirtory.deleteById(id);
            return ResponseEntity.ok("Cliente deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o cliente: " + e.getMessage());
        }
    }
    @Operation(summary = "Update a client", description = "Method that updates a client in the database", tags = {"clients"})
    @PatchMapping("/{id}")
    public Clients updateClients(@PathVariable long id, @RequestBody ClientsDTO payload) {
        Clients existingClients = clientReposirtory.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        BeanUtils.copyProperties(payload, existingClients, NullPropertyNamesUtil.getNullPropertyNames(payload));
        if (payload.address() != null) {
            if (existingClients.getAddress() == null) {
                existingClients.setAddress(new Address(payload.address()));
            } else {
                existingClients.getAddress().update(payload.address());
            }
        }
        return clientReposirtory.save(existingClients);
    }


}
