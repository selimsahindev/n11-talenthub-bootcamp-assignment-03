package com.selimsahin.homework03.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.homework03.constants.Constants;
import com.selimsahin.homework03.dto.WeatherDTO;
import com.selimsahin.homework03.dto.WeatherResponse;
import com.selimsahin.homework03.entity.Weather;
import com.selimsahin.homework03.repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author selimsahindev
 */
@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Clock clock;

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate, Clock clock) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
        this.clock = clock;
    }

    public WeatherDTO getWeatherByCityName(String city) {
        // Get the latest weather from the database if exists.
        Optional<Weather> weatherOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedAtDesc(city);

        // If the weather is not found in the database, get it from the weather stack api.
        if (!weatherOptional.isPresent()) {
            System.out.println("Weather not found in the database.");
            return WeatherDTO.convert(getWeatherFromWeatherStackApi(city));
        }

        // If the weather is older than 30 minutes, get it from the weather stack api.
        if (weatherOptional.get().getUpdatedAt().isBefore(LocalDateTime.now().minusMinutes(30))) {
            System.out.println("Weather is older than 30 minutes.");
            return WeatherDTO.convert(getWeatherFromWeatherStackApi(city));
        }

        return weatherOptional.map(weather -> {
            if (weather.getUpdatedAt().isBefore(LocalDateTime.now().minusMinutes(30))) {
                return WeatherDTO.convert(getWeatherFromWeatherStackApi(city));
            }
            return WeatherDTO.convert(weather);
        }).orElseGet(() -> WeatherDTO.convert(getWeatherFromWeatherStackApi(city)));
    }

    private Weather getWeatherFromWeatherStackApi(String city) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherStackUrl(city), String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeather(city, weatherResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Weather saveWeather(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            Weather weather = new Weather(
                    city,
                    weatherResponse.location().name(),
                    weatherResponse.location().country(),
                    weatherResponse.current().temperature(),
                    getLocalDateTimeNow(),
                    LocalDateTime.parse(weatherResponse.location().localTime(), dateTimeFormatter)
            );
            return weatherRepository.save(weather);
        } catch (Exception e) {
            throw new RuntimeException("Something happened: " + e.getMessage());
        }
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }

    private String getWeatherStackUrl(String city) {
        return Constants.API_URL + Constants.ACCESS_KEY_PARAM + Constants.API_KEY + Constants.QUERY_KEY_PARAM + city;
    }
}
