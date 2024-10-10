package com.chariot.nm.providers.api.controllers;

import com.chariot.nm.providers.api.client.ViaCepClient;
import com.chariot.nm.providers.api.response.EnderecoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/endereco")
public class ViaCepController {

    private final ViaCepClient viaCepClient;

    public ViaCepController(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoResponse> buscarEnderecoPorCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok().body(viaCepClient.buscarEnderecoPorCep(cep));
    }
}
