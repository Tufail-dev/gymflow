package com.gymflow.gymflow.external.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsdaSearchResponse {
    private List<UsdaFoodItem> foods;
}
