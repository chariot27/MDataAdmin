package com.chariot.nm.providers.api.client;

import com.chariot.nm.providers.api.response.CometResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "comet", url = "https://data.nasa.gov/resource/b67r-rgxc.json")
public interface NasaCometClient {

    @GetMapping
    List<CometResponse> buscarComets();
}