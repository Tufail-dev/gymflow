package com.gymflow.gymflow.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UsdaApiService {
    @Value("${usda.api.key}")
    @Value("${usda.api.url}")

    private final RestClient restClient;

    public UsdaApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String searchFood(String foodName){


    }
}
