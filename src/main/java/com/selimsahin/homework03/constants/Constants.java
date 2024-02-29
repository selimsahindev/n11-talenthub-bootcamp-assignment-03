package com.selimsahin.homework03.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author selimsahindev
 */
@Component
public class Constants {

    public static String API_URL;
    public static String API_KEY;
    public static String ACCESS_KEY_PARAM = "?access_key=";
    public static String QUERY_KEY_PARAM = "&query=";

    @Value("${weather.api.url}")
    public void setApiUrl(String apiUrl) {
        Constants.API_URL = apiUrl;
    }

    @Value("${weather.api.access-key}")
    public void setApiKey(String apiKey) {
        Constants.API_KEY = apiKey;
    }
}
