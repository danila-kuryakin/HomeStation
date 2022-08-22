package ru.kuryakin.meteo.web.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuryakin.meteo.web.models.Location;
import ru.kuryakin.meteo.web.models.Weather;

import java.util.List;

@Repository
public interface WebWeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findTop10ByOrderByIdDesc();
    Weather findTopByLocationOrderByIdDesc(Location location);
    List<Weather> findTop300ByLocationOrderByIdDesc(Location location);
    List<Weather> findAllByLocationOrderByIdDesc(Location location, PageRequest pageRequest);
}
