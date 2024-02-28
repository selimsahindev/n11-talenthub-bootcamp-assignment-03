package com.selimsahin.homework03.dto;

/**
 * @author selimsahindev
 */
public record WeatherDTO(
        Request request,
        String cityName,
        String countryName,
        Integer temperature
) {
}
