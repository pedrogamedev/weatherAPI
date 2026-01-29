package com.pedro.weatherAPI.unit.java.com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.Day;
import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import com.pedro.weatherAPI.layers.services.WeatherService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTests {

    @Autowired
    @InjectMocks
    WeatherService service;
    @Mock
    RestTemplate restTemplate;

    WeatherRequest weatherRequest;
    WeatherResponse weatherResponse;
    ArrayList<Day> days;

    public void Init(){
        days = new ArrayList<>();
        weatherRequest = new WeatherRequest("aa", LocalDate.of(2026,01,01),LocalDate.of(2026, 01, 05));
        weatherResponse = new WeatherResponse("a", days);
    }


}
