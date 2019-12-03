package ru.kirill.weatherinformer.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Access(AccessType.FIELD)
@Table(name = "users_weather_informer")
public class User implements Persistable<UUID> {

    @Id
    private UUID id;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime date = LocalDateTime.now();

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "weatherProvider", column = @Column(name = "weather_provider"))})
    private Weather weather;

    public User() {
    }

    public User(UUID id, Weather weather) {
        this.id = id;
        this.weather = weather;
    }

    public User(UUID id) {
        this(id, null);
    }

    public boolean isNew() {
        return id == null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
