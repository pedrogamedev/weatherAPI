package com.pedro.weatherAPI.slice.java.com.pedro.weatherAPI.layers.controller;


import com.pedro.weatherAPI.layers.controller.WeatherController;
import com.pedro.weatherAPI.layers.services.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = WeatherController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WeatherControllerTestsSlice {

    @MockitoBean
    WeatherService weatherService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getWeather_InvalidBody_ReturnsBadRequestAndThrowsHttpMessageNotReadableException() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/weather/get")
                .contentType(MediaType.APPLICATION_JSON).content("{AAAAAA}"));

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(
                result -> assertInstanceOf(HttpMessageNotReadableException.class, result.getResolvedException())
        );
    }

    @Test
    public void getWeather_MissingBody_ReturnsBadRequestAndThrowsHttpMessageNotReadableException() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/weather/get")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(
                result -> assertInstanceOf(HttpMessageNotReadableException.class, result.getResolvedException())
        );
    }
}
