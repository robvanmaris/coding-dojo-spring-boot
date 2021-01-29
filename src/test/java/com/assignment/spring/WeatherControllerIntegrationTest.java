package com.assignment.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.assignment.spring.WeatherEntityFixture.aWeatherEntity;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeatherService service;

    @Test
    void returns_weather_for_city() throws Exception {
        String city = "aCity";

        WeatherEntity weatherEntity = aWeatherEntity();
        when(service.getWeatherForCity(city)).thenReturn(weatherEntity);

        mvc.perform(get("/weather?city=" + city))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id", is(weatherEntity.getId())))
           .andExpect(jsonPath("$.country", is(weatherEntity.getCountry())))
           .andExpect(jsonPath("$.city", is(weatherEntity.getCity())))
           .andExpect(jsonPath("$.temperature", is(weatherEntity.getTemperature())));
    }

}
