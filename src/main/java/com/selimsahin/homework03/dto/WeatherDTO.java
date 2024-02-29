package com.selimsahin.homework03.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.selimsahin.homework03.entity.Weather;

import java.time.LocalDateTime;

/**
 * @author selimsahindev
 */
public record WeatherDTO(
        String cityName,
        String country,
        Integer temperature,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime updatedTime
) {
    public static WeatherDTO convert(Weather from) {
        return new WeatherDTO(
                from.getCityName(),
                from.getCountry(),
                from.getTemperature(),
                from.getUpdatedAt());
    }
}
