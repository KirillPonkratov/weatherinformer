CREATE TABLE IF NOT EXISTS users_weather_informer
(
    id               uuid PRIMARY KEY,
    date             TIMESTAMP DEFAULT now(),
    city             VARCHAR(50),
    weather_provider VARCHAR(50)
);