package com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper mapper;

    @Value("${weather_api}")
    String key;

    public WeatherResponse getWeather(WeatherRequest request){

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        +request.getLocation()+"/"+request.getStartDate()+"/"+ request.getEndDate()+"?key="+key;

        String response = restTemplate.getForObject( url , String.class);


        return mapper.readValue(response, WeatherResponse.class);
    }
}
