package com.assignment.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.api.WeatherResponse;

@Service
public class WeatherService {
    private final ApplicationProperties applicationProperties;
    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;
    private final WeatherEntityMapper weatherEntityMapper;

    public WeatherService(ApplicationProperties applicationProperties,
                          RestTemplate restTemplate,
                          WeatherRepository weatherRepository,
                          WeatherEntityMapper weatherEntityMapper) {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
        this.weatherEntityMapper = weatherEntityMapper;
    }

    WeatherEntity getWeatherForCity(String city) {
        WeatherEntity entity = getWeatherForCity2(city);
        return weatherRepository.save(entity);
    }

    private WeatherEntity getWeatherForCity2(String city) {
        String url = weatherUrlForCity(city);
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);
        return weatherEntityMapper.map(response.getBody());
    }

    private String weatherUrlForCity(String city) {
        String urlTemplate = applicationProperties.getOpenWeatherMapUrlTemplate();
        String appId = applicationProperties.getOpenWeatherMapApiKey();
        return urlTemplate.replace("{city}", city).replace("{appId}", appId);
    }
}
