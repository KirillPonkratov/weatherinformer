DELETE
FROM users_weather_informer;

INSERT INTO users_weather_informer (id, date, city, weather_provider)
VALUES ('ad73085a-fd8b-324d-8b07-c98875c3ae08', '2019-11-27 18:44:17.408000', 'London', 'WORLDWEATHERONLINE'),
       ('8a7b5972-7a2d-3e29-88f3-68791acf1ce0', '2019-11-27 17:47:41.349000', 'Moscow', 'OPENWEATHERMAP'),
       ('e75d93a1-74a7-3305-85cb-c49d302cce68', '2019-11-27 17:50:40.453000', null, null),
       ('50d8cac2-f571-3ec9-a5b3-1a19c2d2a6c8', '2019-11-27 17:55:46.563000', 'Chelyabinsk', 'OPENWEATHERMAP');