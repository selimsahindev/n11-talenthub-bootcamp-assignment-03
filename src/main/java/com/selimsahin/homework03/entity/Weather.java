package com.selimsahin.homework03.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
@Entity
public class Weather {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String requestedCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updatedAt;
    private LocalDateTime responseLocalTime;

    public String getId() {
        return id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }

    public Weather() {
    }

    public Weather(String id, String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedAt, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedAt = updatedAt;
        this.responseLocalTime = responseLocalTime;
    }

    public Weather(String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedAt, LocalDateTime responseLocalTime) {
        this.id = null; // id is auto-generated
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedAt = updatedAt;
        this.responseLocalTime = responseLocalTime;
    }
}
