package com.assignment.spring;

import org.junit.jupiter.api.Test;

import com.assignment.spring.api.Main;
import com.assignment.spring.api.Sys;
import com.assignment.spring.api.WeatherResponse;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherEntityMapperTest {
    private final WeatherEntityMapper mapper = new WeatherEntityMapper();

    @Test
    void maps_weather_response_to_weather_entity() {
        WeatherResponse response = new WeatherResponse();
        response.setName("name1");
        response.setSys(sysWithCountry("country1"));
        response.setMain(mainWithTemp(12.34));

        WeatherEntity result = mapper.map(response);

        WeatherEntity expected = new WeatherEntity();
        expected.setCity(response.getName());
        expected.setCountry(response.getSys().getCountry());
        expected.setTemperature(response.getMain().getTemp());

        assertThat(result).isEqualToComparingFieldByField(expected);
    }

    private Main mainWithTemp(double temp) {
        Main main = new Main();
        main.setTemp(temp);
        return main;
    }

    private Sys sysWithCountry(String country) {
        Sys sys = new Sys();
        sys.setCountry(country);
        return sys;
    }
}
