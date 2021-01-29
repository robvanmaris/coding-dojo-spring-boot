package com.assignment.spring;

import org.springframework.stereotype.Component;

import com.assignment.spring.api.WeatherResponse;

@Component
public class WeatherEntityMapper {
    public WeatherEntity map(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCity(response.getName());
        entity.setCountry(response.getSys().getCountry());
        entity.setTemperature(response.getMain().getTemp());
        return entity;
    }
}
