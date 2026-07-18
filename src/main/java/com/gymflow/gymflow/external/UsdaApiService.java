package com.gymflow.gymflow.external;

import com.gymflow.gymflow.dto.FoodResponceDto;
import com.gymflow.gymflow.external.dto.UsdaFoodItem;
import com.gymflow.gymflow.external.dto.UsdaSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UsdaApiService {

    @Value("${usda.api.key}")
    private String apiKey;

    @Value("${usda.api.url}")
    private String apiUrl;

    private final RestClient restClient;

    public UsdaApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public UsdaSearchResponse searchFood(String foodName) {
        return restClient.get()
                .uri(apiUrl + "?query=" + foodName + "&api_key=" + apiKey)
                .retrieve()
                .body(UsdaSearchResponse.class);
    }
}