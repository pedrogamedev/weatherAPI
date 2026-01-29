package com.pedro.weatherAPI.layers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherResponse {

    String resolvedAddress;
    ArrayList<Day> days;
}
