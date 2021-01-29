package com.assignment.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.assignment.spring.api.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @InjectMocks
    private WeatherService service;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherRepository repository;

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private WeatherEntityMapper mapper;

    @Test
    void calls_OpenWeatherMap_and_returns_saved_result() {
        final String city = "city1";

        WeatherResponse body = new WeatherResponse();
        ResponseEntity<WeatherResponse> response = new ResponseEntity<>(body, OK);

        WeatherEntity weatherEntity1 = new WeatherEntity();
        WeatherEntity weatherEntity2 = new WeatherEntity();

        when(applicationProperties.getOpenWeatherMapApiKey()).thenReturn("apiKey1");
        when(applicationProperties.getOpenWeatherMapUrlTemplate()).thenReturn("url_for_city_{city}_and_appId_{appId}");

        when(restTemplate.getForEntity("url_for_city_city1_and_appId_apiKey1", WeatherResponse.class))
                .thenReturn(response);

        when(mapper.map(same(body))).thenReturn(weatherEntity1);
        when(repository.save(same(weatherEntity1))).thenReturn(weatherEntity2);

        WeatherEntity result = service.getWeatherForCity(city);

        assertThat(result).isSameAs(weatherEntity2);
    }
}
