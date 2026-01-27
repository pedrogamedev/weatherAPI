package com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public WeatherResponse getWeather(WeatherRequest request){
        return new WeatherResponse();
    }
}
