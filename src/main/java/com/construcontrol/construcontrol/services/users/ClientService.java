package com.construcontrol.construcontrol.services.users;

import com.construcontrol.construcontrol.DTO.users.ClientDTO;
import com.construcontrol.construcontrol.model.domain.users.Client;
import com.construcontrol.construcontrol.repositories.users.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientDTO criarClient(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        return convertToDTO(clientRepository.save(client));
    }

    public List<ClientDTO> listarClients() {
        return clientRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClientDTO> buscarClientPorId(Long id) {
        return clientRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<ClientDTO> atualizarClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id).map(client -> {
            client.setId(clientDTO.getId());
            client.setName(clientDTO.getName());
            client.setCpf(clientDTO.getCpf());
            client.setRg(client.getRg());
            client.setPhone(clientDTO.getPhone());
            client.setEmail(clientDTO.getEmail());
            client.setPassword(clientDTO.getPassword());
            client.setUserRole(clientDTO.getUserRole());
            client.setMaritalStatus(clientDTO.getMaritalStatus());
            return convertToDTO(clientRepository.save(client));
        });
    }

    public boolean deletarClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getRg(),
                client.getPhone(),
                client.getEmail(),
                client.getPassword(),
                client.getUserRole(),
                client.getMaritalStatus()
        );
    }

    private Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setRg(client.getRg());
        client.setPhone(clientDTO.getPhone());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());
        client.setUserRole(clientDTO.getUserRole());
        client.setMaritalStatus(clientDTO.getMaritalStatus());
        return client;
    }

}


