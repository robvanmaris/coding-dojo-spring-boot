package com.assignment.spring;

import com.assignment.spring.WeatherEntity;

public class WeatherEntityFixture {
    public static WeatherEntity aWeatherEntity() {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCountry("country1");
        weatherEntity.setCity("city1");
        weatherEntity.setId(123);
        weatherEntity.setTemperature(12.23);
        return weatherEntity;
    }

    public static WeatherEntity aWeatherEntityWithoutId() {
        final WeatherEntity weatherEntity = aWeatherEntity();
        weatherEntity.setId(null);
        return weatherEntity;
    }
}
