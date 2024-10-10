package com.chariot.nm.providers.api.client;

import com.chariot.nm.providers.api.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="https://viacep.com.br/ws/",name="viacepAPI")
public interface ViaCepClient {

    @GetMapping("{cep}/json")
    EnderecoResponse buscarEnderecoPorCep(@PathVariable("cep") String cep);

}
