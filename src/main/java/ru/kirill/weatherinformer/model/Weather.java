package ru.kirill.weatherinformer.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Embeddable
public class Weather {

    @Transient
    private Integer temperature;

    @Transient
    private Integer humidity;

    @Transient
    private LocalDateTime dateTime = LocalDateTime.now();

    @Transient
    private String description;

    private String city;

    @Enumerated(EnumType.STRING)
    private WeatherProvider weatherProvider;

    public Weather() {
    }

    public Weather(String city, WeatherProvider weatherProvider) {
        this(null, null, null, city, weatherProvider);
    }

    public Weather(Integer temperature, Integer humidity, String description, String city, WeatherProvider weatherProvider) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
        this.city = city;
        this.weatherProvider = weatherProvider;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
}
