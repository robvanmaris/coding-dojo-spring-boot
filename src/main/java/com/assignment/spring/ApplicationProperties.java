package com.assignment.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private String openWeatherMapApiKey;
    private String openWeatherMapUrlTemplate;

    public String getOpenWeatherMapApiKey() {
        return openWeatherMapApiKey;
    }

    public void setOpenWeatherMapApiKey(String openWeatherMapApiKey) {
        this.openWeatherMapApiKey = openWeatherMapApiKey;
    }

    public String getOpenWeatherMapUrlTemplate() {
        return openWeatherMapUrlTemplate;
    }

    public void setOpenWeatherMapUrlTemplate(String openWeatherMapUrlTemplate) {
        this.openWeatherMapUrlTemplate = openWeatherMapUrlTemplate;
    }
}
