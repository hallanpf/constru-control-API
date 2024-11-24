package com.construcontrol.construcontrol.services.users;

import com.construcontrol.construcontrol.DTO.users.ManagerDTO;
import com.construcontrol.construcontrol.model.domain.users.Manager;
import com.construcontrol.construcontrol.repositories.users.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerDTO criarManager(ManagerDTO managerDTO) {
        Manager manager = convertToEntity(managerDTO);
        return convertToDTO(managerRepository.save(manager));
    }

    public List<ManagerDTO> listarManagers() {
        return managerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ManagerDTO> buscarManagerPorId(Long id) {
        return managerRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<ManagerDTO> atualizarManager(Long id, ManagerDTO managerDTO) {
        return managerRepository.findById(id).map(manager -> {
            manager.setId(managerDTO.getId());
            manager.setName(managerDTO.getName());
            manager.setCpf(managerDTO.getCpf());
            manager.setRg(manager.getRg());
            manager.setPhone(managerDTO.getPhone());
            manager.setEmail(managerDTO.getEmail());
            manager.setPassword(managerDTO.getPassword());
            manager.setUserRole(managerDTO.getUserRole());
            return convertToDTO(managerRepository.save(manager));
        });
    }

    public boolean deletarManager(Long id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ManagerDTO convertToDTO(Manager manager) {
        return new ManagerDTO(
                manager.getId(),
                manager.getName(),
                manager.getCpf(),
                manager.getRg(),
                manager.getPhone(),
                manager.getEmail(),
                manager.getPassword(),
                manager.getUserRole()
        );
    }

    private Manager convertToEntity(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        manager.setId(managerDTO.getId());
        manager.setName(managerDTO.getName());
        manager.setCpf(managerDTO.getCpf());
        manager.setRg(manager.getRg());
        manager.setPhone(managerDTO.getPhone());
        manager.setEmail(managerDTO.getEmail());
        manager.setPassword(managerDTO.getPassword());
        manager.setUserRole(managerDTO.getUserRole());
        return manager;
    }

}
