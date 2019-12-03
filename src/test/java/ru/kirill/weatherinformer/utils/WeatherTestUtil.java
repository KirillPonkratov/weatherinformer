package ru.kirill.weatherinformer.utils;

import ru.kirill.weatherinformer.model.User;
import ru.kirill.weatherinformer.model.Weather;
import ru.kirill.weatherinformer.model.WeatherProvider;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherTestUtil {
    public static final String UUIDCREATED = "b9405bc5-1162-349f-966f-5f639c6c1bec";
    public static final String UUIDNEW = "50d8cac2-f571-3ec9-a5b3-1a19c2d2a6c8";
    public static final User USER = new User(UUID.nameUUIDFromBytes("b9405bc5-1162-349f-966f-5f639c6c1bec".getBytes()), new Weather("Moscow", WeatherProvider.OPENWEATHERMAP));

    public static User getNew() {
        return new User(UUID.nameUUIDFromBytes(UUIDNEW.getBytes()), new Weather("Chelyabinsk", WeatherProvider.OPENWEATHERMAP));
    }

    public static User getUpdated() {
        return new User(UUID.nameUUIDFromBytes(UUIDCREATED.getBytes()), new Weather("Chelyabinsk", WeatherProvider.WORLDWEATHERONLINE));
    }

    public static void assertWithWeather(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "weather", "date");
        assertThat(actual.getWeather()).isEqualToComparingOnlyGivenFields(expected.getWeather(), "city", "weatherProvider");
    }

    public static void assertWithoutWeather(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "weather", "date");
    }
}
