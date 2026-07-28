package com.gymflow.gymflow.external;

import com.gymflow.gymflow.external.dto.ScaledFoodResponse;
import com.gymflow.gymflow.external.dto.UsdaFoodDetailsResponse;
import com.gymflow.gymflow.external.dto.UsdaFoodNutrientDetail;
import com.gymflow.gymflow.external.dto.UsdaSearchResponse;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Service
public class UsdaApiService {

    @Value("${usda.api.key}")
    private String apiKey;

    @Value("${usda.api.url}")
    private String apiUrl;
    @Value("${usda.api.detail.url}")
    private String detailUrl;
    private final Bucket bucket = Bucket.builder()
            .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
            .build();
    private final RestClient restClient;

    public UsdaApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public UsdaSearchResponse searchFood(String foodName) {

        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Too many requests. Please try again later.");
        }
        return restClient.get()
                .uri(apiUrl + "?query=" + foodName + "&api_key=" + apiKey)
                .retrieve()
                .body(UsdaSearchResponse.class);
    }

    public UsdaFoodDetailsResponse getFoodDetails(Long fdcId) {
        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Too many requests. Please try again later.");
        }
        String url = detailUrl + "/" + fdcId + "?api_key=" + apiKey;
        System.out.println("Calling USDA URL: " + url);
        return restClient.get()
                .uri(detailUrl + "/" + fdcId + "?api_key=" + apiKey)
                .retrieve()
                .body(UsdaFoodDetailsResponse.class);
    }

    public ScaledFoodResponse getScaledFoodDetails(Long fdcId, Double grams) {
        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Too many requests. Please try again later.");
        }
        UsdaFoodDetailsResponse details = getFoodDetails(fdcId);

        double calories = 0;
        double protein = 0;
        double fat = 0;
        double carbs = 0;

        for (UsdaFoodNutrientDetail nutrient : details.getFoodNutrients()) {
            String name = nutrient.getNutrient().getName();

            if (name.equals("Energy")) {
                calories = nutrient.getAmount();
            } else if (name.equals("Protein")) {
                protein = nutrient.getAmount();
            } else if (name.equals("Total lipid (fat)")) {
                fat = nutrient.getAmount();
            } else if (name.equals("Carbohydrate, by difference")) {
                carbs = nutrient.getAmount();
            }
        }

        double scaledCalories = (calories / 100) * grams;
        double scaledProtein = (protein / 100) * grams;
        double scaledFat = (fat / 100) * grams;
        double scaledCarbs = (carbs / 100) * grams;

        return new ScaledFoodResponse(
                details.getDescription(),
                grams,
                scaledCalories,
                scaledProtein,
                scaledFat,
                scaledCarbs
        );
    }
}