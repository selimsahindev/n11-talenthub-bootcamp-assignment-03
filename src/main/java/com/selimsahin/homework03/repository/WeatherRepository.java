package com.selimsahin.homework03.repository;

import com.selimsahin.homework03.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author selimsahindev
 */
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findFirstByRequestedCityNameOrderByUpdatedAtDesc(String cityName);
}
