package ru.kirill.weatherinformer.service;

import ru.kirill.weatherinformer.model.User;
import ru.kirill.weatherinformer.model.Weather;
import ru.kirill.weatherinformer.repository.UserRepository;
import ru.kirill.weatherinformer.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WeatherService {

    @Autowired
    private UserRepository repository;

    public User save(String userId, Weather weather) {
        User user = repository.save(new User(UUID.nameUUIDFromBytes(userId.getBytes()), weather));
        WeatherUtil.parseWeather(user.getWeather());
        return user;
    }

    public User get(String userId) {
        if (userId != null) {
            Optional<User> userOptional = repository.findById(UUID.nameUUIDFromBytes(userId.getBytes()));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                WeatherUtil.parseWeather(user.getWeather());
                return user;
            }
        }
        return null;
    }
}
