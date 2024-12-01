package com.construcontrol.construcontrol.controllers.users;

import com.construcontrol.construcontrol.DTO.users.ClientDTO;
import com.construcontrol.construcontrol.services.users.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/client")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ClientController {
  private final ClientService clientService;

  @Operation(summary = "Create a client", description = "Method that creates a client in the database", tags = {"clients"})
  @PostMapping
  public ResponseEntity<ClientDTO> criarClient(@RequestBody @Validated ClientDTO clientDTO) {
    log.info("Chamando criarClient no ClientController com dados: {}", clientDTO);
    ClientDTO newClient = clientService.criarClient(clientDTO);
    return ResponseEntity.ok(newClient);
  }

  @Operation(summary = "Get all clients", description = "Method that returns all clients registered in the database", tags = {"clients"})
  @GetMapping
  public ResponseEntity<List<ClientDTO>> listarClients() {
    log.info("Chamando listarClients no ClientController");
    List<ClientDTO> clients = clientService.listarClients();
    return ResponseEntity.ok(clients);
  }

  @Operation(summary = "Get clients by id", description = "Method that returns a client registered in the database by id", tags = {"clients"})
  @GetMapping("/{id}")
  public ResponseEntity<ClientDTO> buscarClientPorId(@PathVariable long id) {
    log.info("Chamando buscarClientPorId no ClientController com id: {}", id);
    Optional<ClientDTO> client = clientService.buscarClientPorId(id);
    return client.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Update a client", description = "Method that updates a client in the database", tags = {"clients"})
  @PatchMapping("/{id}")
  public ResponseEntity<ClientDTO> atualizarClient(@PathVariable long id, @RequestBody ClientDTO clientDTO) {
    log.info("Chamando atualizarClient no ClientController com id: {} e dados: {}", id, clientDTO);
    Optional<ClientDTO> clientAtualizado = clientService.atualizarClient(id, clientDTO);
    return clientAtualizado.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Delete an client", description = "Method that deletes an client in the database", tags = {"clients"})
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarClient(@PathVariable long id) {
    log.info("Chamando deletarClient no ClientController com id: {}", id);
    boolean deletado = clientService.deletarClient(id);
    if (deletado) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
