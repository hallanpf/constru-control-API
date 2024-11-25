package com.construcontrol.construcontrol.services.projects;

import com.construcontrol.construcontrol.client.ViaCepClient;
import com.construcontrol.construcontrol.DTO.projects.AddressDTO;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {
    private final ViaCepClient viaCepClient;

    public ViaCepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public AddressDTO findAddress(String cep) {
        try {
            String sanitizedCep = cep.replaceAll("\\D", "");
            return viaCepClient.findAddressByCep(sanitizedCep);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar endereço: " + e.getMessage(), e);
        }
    }

}
