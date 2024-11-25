package com.construcontrol.construcontrol.client;

import com.construcontrol.construcontrol.DTO.projects.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ViaCepClient",
        url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    AddressDTO findAddressByCep(@PathVariable("cep") String cep);

}