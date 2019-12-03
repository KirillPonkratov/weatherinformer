package ru.kirill.weatherinformer.web;

import ru.kirill.weatherinformer.model.User;
import ru.kirill.weatherinformer.model.Weather;
import ru.kirill.weatherinformer.model.WeatherProvider;
import ru.kirill.weatherinformer.service.WeatherService;
import ru.kirill.weatherinformer.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping
    public Weather getWeather(HttpServletRequest request, HttpServletResponse response) {
        String userId = WeatherUtil.getUserId(request, response);
        String stringTimeStamp = request.getHeader("If-None-Match");
        if (stringTimeStamp != null) {
            LocalDateTime timeStamp = LocalDateTime.parse(stringTimeStamp);
            int i = 0;

            //Check date header. It's valid for 5 minutes.
            if (timeStamp.compareTo(LocalDateTime.now()
                    .minus(300, ChronoUnit.SECONDS)) < 0) {
                int j = 0;
                //setting 304 and returning with empty body
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return null;
            }
        }

        //Add ETag date header to response. Needed to use browser cache.
        response.addHeader("ETag", LocalDateTime.now().toString());
        User user = service.get(userId);
        return user == null ? new Weather() : user.getWeather();
    }

    @PostMapping
    public Weather saveUserWithWeather(HttpServletRequest request, HttpServletResponse response) {
        String userId = WeatherUtil.getUserId(request, response);
        String city = request.getParameter("city");
        WeatherProvider weatherProvider = WeatherProvider.valueOf(request.getParameter("weatherProvider"));
        return service.save(userId, new Weather(city, weatherProvider)).getWeather();
    }
}

