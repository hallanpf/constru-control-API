package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.ClientsDTO;
import com.construcontrol.construcontrol.model.domain.users.Clients;
import com.construcontrol.construcontrol.repositories.users.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping
    public ResponseEntity createClient(@RequestBody @Validated ClientsDTO payload) {
        Clients clients;
        try {
            clients = new Clients(payload);
            clientReposirtory.save(clients);
            System.out.println(payload);
            return ResponseEntity.ok(payload);
        } catch (Exception e) {
            System.out.println(payload);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o cliente: " + e.getMessage());
        }
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
    public ResponseEntity updateClient(@PathVariable long id, @RequestBody @Validated ClientsDTO payload) {
        try {
            var client = clientReposirtory.getClientsById(id);
            client.update(payload);
            clientReposirtory.save(client);
            return ResponseEntity.ok("Cliente atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o cliente: " + e.getMessage());
        }

    }


}
