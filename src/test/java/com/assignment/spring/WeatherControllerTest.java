package com.assignment.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import static com.assignment.spring.WeatherEntityFixture.aWeatherEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {
    @InjectMocks
    private WeatherController controller;

    @Mock
    private WeatherService service;

    @Test
    void calls_service() {
        final String city = "city1";
        MockHttpServletRequest request = requestWithCityParameter(city);

        final WeatherEntity weatherEntity = aWeatherEntity();
        when(service.getWeatherForCity(city)).thenReturn(weatherEntity);

        WeatherEntity result = controller.weather(request);

        assertThat(result).isSameAs(weatherEntity);
    }

    private MockHttpServletRequest requestWithCityParameter(String city) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("city", city);
        return request;
    }
}
