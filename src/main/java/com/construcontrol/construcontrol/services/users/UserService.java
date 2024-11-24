package com.construcontrol.construcontrol.services.users;

import com.construcontrol.construcontrol.DTO.users.UserDTO;
import com.construcontrol.construcontrol.model.domain.users.User;
import com.construcontrol.construcontrol.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDTO criarUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return convertToDTO(userRepository.save(user));
    }

    public List<UserDTO> listarUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> buscarUserPorId(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> atualizarUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setId(userDTO.getId());
            user.setName(userDTO.getName());
            user.setCpf(userDTO.getCpf());
            user.setRg(user.getRg());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setUserRole(userDTO.getUserRole());
            return convertToDTO(userRepository.save(user));
        });
    }

    public boolean deletarUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getRg(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getUserRole()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setCpf(userDTO.getCpf());
        user.setRg(user.getRg());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUserRole(userDTO.getUserRole());
        return user;
    }

}
