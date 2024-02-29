package com.selimsahin.homework03.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author selimsahindev
 */
public record Location(
        String name,
        String country,
        String region,
        Double lat,
        Double lon,
        @JsonProperty("timezone_id")
        String timezoneId,
        @JsonProperty("localtime")
        String localTime,
        @JsonProperty("localtime_epoch")
        String localtimeEpoch,
        @JsonProperty("utc_offset")
        String utcOffset
) {
}
