package ru.kuryakin.meteo.collector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuryakin.meteo.collector.models.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
