package com.pedro.weatherAPI.unit.java.com.pedro.weatherAPI.layers.controller;

import com.pedro.weatherAPI.layers.controller.WeatherController;
import com.pedro.weatherAPI.layers.domain.model.Day;
import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import com.pedro.weatherAPI.layers.services.WeatherService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTests {

    @Autowired
    @InjectMocks
    WeatherController weatherController;
    @Mock
    WeatherService weatherService;

    WeatherRequest weatherRequest;
    WeatherResponse weatherResponse;
    ArrayList<Day> days;

    public void Init(){
        days = new ArrayList<>();
        weatherRequest = new WeatherRequest("aa", LocalDate.of(2026,01,01),LocalDate.of(2026, 01, 05));
        weatherResponse = new WeatherResponse("a", days);
    }

    @Test
    public void getWeather_ValidaDate_ReturnsOkAndTemp(){
        when(weatherService.getWeather(weatherRequest)).thenReturn(weatherResponse);

        ResponseEntity<WeatherResponse> response = weatherController.getWeather(weatherRequest);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(weatherService).getWeather(weatherRequest);
    }



}
