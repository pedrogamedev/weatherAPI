package com.pedro.weatherAPI.layers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class WeatherRequest {

    String location;
    LocalDate startDate;
    LocalDate endDate;
}
