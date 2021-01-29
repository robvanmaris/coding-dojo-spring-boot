package com.assignment.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.assignment.spring.WeatherEntityFixture.aWeatherEntityWithoutId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class WeatherReposityIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WeatherRepository repository;

    @Test
    void persists_weather_entity() {
        WeatherEntity weatherEntity = aWeatherEntityWithoutId();

        WeatherEntity saved = repository.save(weatherEntity);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCity()).isEqualTo(weatherEntity.getCity());
        assertThat(saved.getCountry()).isEqualTo(weatherEntity.getCountry());
        assertThat(saved.getTemperature()).isEqualTo(weatherEntity.getTemperature());

        WeatherEntity fetched = repository.findById(saved.getId()).get();
        assertThat(fetched).isEqualToComparingFieldByField(saved);
    }
}
