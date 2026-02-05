package com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import com.pedro.weatherAPI.others.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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

    public WeatherResponse getWeather(WeatherRequest request) throws HttpClientErrorException {

        DateValidator.validateNotFutureDate(request.getStartDate());
        DateValidator.validateNotFutureDate(request.getEndDate());
        DateValidator.validateEndAfterStart(request.getStartDate(), request.getEndDate());
        String url;
        try{
            url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    +request.getLocation()+"/"+request.getStartDate()+"/"+ request.getEndDate()+"?key="+key;
        }
        catch (HttpClientErrorException e){
            throw new HttpClientErrorException(e.getStatusCode());
        }

        String response = restTemplate.getForObject( url , String.class);


        return mapper.readValue(response, WeatherResponse.class);


    }
}
