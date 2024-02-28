package com.selimsahin.homework03.dto;

/**
 * @author selimsahindev
 */
public record WeatherResponse(
        Request request,
        Location location,
        Current current
) {
}
