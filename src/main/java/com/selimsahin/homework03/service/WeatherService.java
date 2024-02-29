package com.selimsahin.homework03.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.homework03.dto.WeatherDTO;
import com.selimsahin.homework03.dto.WeatherResponse;
import com.selimsahin.homework03.entity.Weather;
import com.selimsahin.homework03.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.access-key}")
    private String weatherApiAccessKey;

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

        Optional<Weather> weatherOptional = weatherRepository.findFirstByCityNameOrderByUpdatedAtDesc(city);

        if (!weatherOptional.isPresent()) {
            return WeatherDTO.convert(getWeatherFromWeatherStackApi(city));
        }

        return WeatherDTO.convert(weatherOptional.get());
    }

    private Weather getWeatherFromWeatherStackApi(String city) {
        String url = this.weatherApiUrl + "?access_key=" + this.weatherApiAccessKey + "&query=" + city;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

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
}
