package com.pedro.weatherAPI.layers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Day {
    String datetime;
    Double temp;
    Double feelslike;
    String description;
}
