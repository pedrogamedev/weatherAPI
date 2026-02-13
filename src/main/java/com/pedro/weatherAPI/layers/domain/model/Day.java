package com.pedro.weatherAPI.layers.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Day {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("feelslike")
    private double feelslike;

    @JsonProperty("description")
    private String description;
}
