package com.chariot.nm.providers.api.controllers;

import com.chariot.nm.providers.api.client.NasaCometClient;
import com.chariot.nm.providers.api.response.CometResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/comets")
public class NasaCometController {

    private final NasaCometClient cometClient;

    public NasaCometController(NasaCometClient cometClient) {
        this.cometClient = cometClient;
    }

    @GetMapping
    public ResponseEntity<List<CometResponse>> buscarComets() {
        List<CometResponse> comets = cometClient.buscarComets();
        return ResponseEntity.ok(comets);
    }
}