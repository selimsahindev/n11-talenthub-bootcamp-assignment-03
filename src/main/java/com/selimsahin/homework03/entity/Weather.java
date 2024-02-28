package com.selimsahin.homework03.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestedCityName;
    private String cityName;
    private String countryName;
    private Integer temperature;
    private LocalDateTime updatedAt;
    private LocalDateTime responseLocalTime;

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
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

    public Weather(Long id, String requestedCityName, String cityName, String countryName, Integer temperature, LocalDateTime updatedAt, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.temperature = temperature;
        this.updatedAt = updatedAt;
        this.responseLocalTime = responseLocalTime;
    }

    public Weather(String requestedCityName, String cityName, String countryName, Integer temperature, LocalDateTime updatedAt, LocalDateTime responseLocalTime) {
        this.id = null; // id is auto-generated
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.countryName = countryName;
        this.temperature = temperature;
        this.updatedAt = updatedAt;
        this.responseLocalTime = responseLocalTime;
    }
}
