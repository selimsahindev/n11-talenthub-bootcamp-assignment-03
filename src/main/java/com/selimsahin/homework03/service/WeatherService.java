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

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    public WeatherDTO getWeatherByCityName(String city) {

        Optional<Weather> weatherOptional = weatherRepository.findFirstByCityNameOrderByUpdatedAtDesc(city);

        if (!weatherOptional.isPresent()) {
            return WeatherDTO.convert(getWeatherFromWeatherStackApi(city));
        }

        return WeatherDTO.convert(weatherOptional.get());
    }

    private Weather getWeatherFromWeatherStackApi(String city) {
        String weatherApiUrl = this.weatherApiUrl + "?access_key=" + this.weatherApiAccessKey + "&query=" + city;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(weatherApiUrl, String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.convertValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeather(city, weatherResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Weather saveWeather(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Weather weather = new Weather(
                city,
                weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localTime(), dateTimeFormatter)
        );

        return weatherRepository.save(weather);
    }
}
