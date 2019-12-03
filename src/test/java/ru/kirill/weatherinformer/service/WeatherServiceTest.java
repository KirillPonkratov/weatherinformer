package ru.kirill.weatherinformer.service;

import ru.kirill.weatherinformer.model.User;
import ru.kirill.weatherinformer.model.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kirill.weatherinformer.utils.WeatherTestUtil;

@ContextConfiguration("classpath:spring/spring-inmemory.xml")
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/fillDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class WeatherServiceTest {

    @Autowired
    WeatherService service;

    @Test
    public void createWithWeather() throws Exception {
        User newUser = WeatherTestUtil.getNew();
        User created = service.save(WeatherTestUtil.UUIDNEW, newUser.getWeather());
        WeatherTestUtil.assertWithWeather(created, newUser);
        WeatherTestUtil.assertWithWeather(service.get(WeatherTestUtil.UUIDNEW), newUser);
    }

    @Test(expected = NullPointerException.class)
    public void duplicateIdCreate() throws Exception {
        service.save(WeatherTestUtil.UUIDCREATED, new Weather());
    }

    @Test
    public void get() {
        User user = service.get(WeatherTestUtil.UUIDCREATED);
        WeatherTestUtil.assertWithWeather(user, WeatherTestUtil.USER);
    }

    @Test
    public void update() throws Exception {
        User updated = WeatherTestUtil.getUpdated();
        service.save(WeatherTestUtil.UUIDCREATED, updated.getWeather());
        WeatherTestUtil.assertWithWeather(service.get(WeatherTestUtil.UUIDCREATED), updated);
    }
}