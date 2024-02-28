package com.selimsahin.homework03.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author selimsahindev
 */
public record Location(
        String name,
        String country,
        @JsonProperty("localtime")
        String localTime
) {
}
