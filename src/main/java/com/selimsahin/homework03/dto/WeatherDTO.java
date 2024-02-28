package com.selimsahin.homework03.dto;

import com.selimsahin.homework03.entity.Weather;

/**
 * @author selimsahindev
 */
public record WeatherDTO(
        String cityName,
        String countryName,
        Integer temperature
) {
    public static WeatherDTO convert(Weather from) {
        return new WeatherDTO(
                from.getCityName(),
                from.getCountryName(),
                from.getTemperature()
        );
    }
}
