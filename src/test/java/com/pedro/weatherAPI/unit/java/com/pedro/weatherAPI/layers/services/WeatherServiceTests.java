package com.pedro.weatherAPI.unit.java.com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.Day;
import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import com.pedro.weatherAPI.layers.services.WeatherService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTests {

    @Autowired
    @InjectMocks
    WeatherService service;
    @Mock
    RestTemplate restTemplate;
    @Mock
    ObjectMapper mapper;

    WeatherRequest weatherRequest;
    WeatherResponse weatherResponse;
    ArrayList<Day> days;
    String jsonResponse;

    @BeforeEach
    public void Init(){
        days = new ArrayList<>();
        weatherRequest = new WeatherRequest("aa", LocalDate.of(2026,01,01),LocalDate.of(2026, 01, 05));
        weatherResponse = new WeatherResponse("a", days);
        jsonResponse ="json";
    }

    @Test
    public void getWeather_ValidWeatherRequest_ReturnsWeatherResponse(){
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class))).thenReturn(jsonResponse);
        when(mapper.readValue(jsonResponse, WeatherResponse.class)).thenReturn(weatherResponse);

        WeatherResponse response = service.getWeather(weatherRequest);

        Assertions.assertThat(response).isNotNull();
        verify(restTemplate).getForObject(Mockito.anyString(), Mockito.eq(String.class));
        verify(mapper).readValue(jsonResponse, WeatherResponse.class);
    }


}
