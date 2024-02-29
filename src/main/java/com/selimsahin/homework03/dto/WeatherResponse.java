package com.selimsahin.homework03.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author selimsahindev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
        Request request,
        Location location,
        Current current
) {
}
