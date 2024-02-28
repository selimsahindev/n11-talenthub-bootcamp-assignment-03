package com.selimsahin.homework03.dto;

/**
 * @author selimsahindev
 */
public record Request(
        String type,
        String query,
        String language,
        String unit
) {
}
