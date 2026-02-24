package com.pedro.weatherAPI.unit.java.com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.Day;
import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import com.pedro.weatherAPI.layers.services.WeatherService;
import com.pedro.weatherAPI.others.DateValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTests {

    @Autowired
    @InjectMocks
    WeatherService service;
    @Mock
    RestTemplate restTemplate;
    @Mock
    ObjectMapper mapper;
    @Mock
     RedisTemplate<String, Object> redisTemplate;

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
        when(mapper.readValue(Mockito.anyString(), Mockito.any(TypeReference.class))).thenReturn(weatherResponse);
        try (MockedStatic<DateValidator> mocked =
                     mockStatic(DateValidator.class)) {
            WeatherResponse response = service.getWeather(weatherRequest);

            Assertions.assertThat(response).isNotNull();
            verify(restTemplate).getForObject(Mockito.anyString(), Mockito.eq(String.class));
            verify(mapper).readValue(Mockito.anyString(), Mockito.any(TypeReference.class));

            mocked.verify(() ->{
                DateValidator.validateNotFutureDate(weatherRequest.getStartDate());
                DateValidator.validateNotFutureDate(weatherRequest.getEndDate());
                DateValidator.validateEndAfterStart(weatherRequest.getStartDate(), weatherRequest.getEndDate());
                DateValidator.validateHowManyDays(weatherRequest.getStartDate(), weatherRequest.getEndDate());
            } );
        }
    }

    @Test
    public void getWeather_ApiRateLimitExceeded_ThrowsHttpClientErrorException() {
        HttpClientErrorException tooManyRequestsException = new HttpClientErrorException(
                HttpStatus.TOO_MANY_REQUESTS, "3rd party request limit exceeded,");

        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(tooManyRequestsException);

        HttpClientErrorException thrownException = assertThrows(HttpClientErrorException.class, () -> {
            service.getWeather(weatherRequest);
        });

        Assertions.assertThat(thrownException.getStatusCode()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);
        Assertions.assertThat(thrownException.getMessage()).contains("3rd party request limit exceeded,");
    }

    @Test
    public void getWeather_ApiThrowsUnexpectedException_ThrowsException() {
        RuntimeException unexpectedException = new RuntimeException("Unexpected error occurred");

        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(unexpectedException);

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            service.getWeather(weatherRequest);
        });

        Assertions.assertThat(thrownException.getMessage()).contains("Unexpected error occurred");
    }
}
