package com.pedro.weatherAPI.layers.services;

import com.pedro.weatherAPI.layers.domain.model.WeatherRequest;
import com.pedro.weatherAPI.layers.domain.model.WeatherResponse;
import com.pedro.weatherAPI.others.DateValidator;
import com.pedro.weatherAPI.others.exceptions.CityNotFoundException;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Set;

@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;



    @Autowired
    ObjectMapper mapper;

    @Value("${weather_api}")
    String key;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = "weatherCache", key = "T(String).format('%s_%s_%s', #request.location, #request.startDate, #request.endDate)")
    public WeatherResponse getWeather(WeatherRequest request) throws HttpClientErrorException {

        DateValidator.validateNotFutureDate(request.getStartDate());
        DateValidator.validateNotFutureDate(request.getEndDate());
        DateValidator.validateEndAfterStart(request.getStartDate(), request.getEndDate());
        DateValidator.validateHowManyDays(request.getStartDate(), request.getEndDate());

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                +request.getLocation()+"/"+request.getStartDate()+"/"+ request.getEndDate()+"?key="+key;
        String response;
        try{
            response = restTemplate.getForObject(url, String.class);
        }
        catch (HttpClientErrorException e){
            if(e.getMessage().contains("Bad API Request:No valid locations could be determined from the input")){
                throw new CityNotFoundException("City "+ request.getLocation() + " not found exception.");
            }
            throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, "3rd party API error.");
        }
        enforceLimit();

        return mapper.readValue(response, new TypeReference<WeatherResponse>() {});
    }

    public void enforceLimit() {
        Set<String> keys = redisTemplate.keys("weatherCache::*");

        if (keys.size() > 5) {
            redisTemplate.delete(keys.iterator().next());
        }
    }
}
