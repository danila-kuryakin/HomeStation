package ru.kuryakin.meteo.collector.repository;

import org.springframework.stereotype.Repository;
import ru.kuryakin.meteo.collector.models.Weather;

@Repository
public interface WeatherRepository {
    /***
     * Добавление данных о погоде в базу данных
     * @param weather - данные о погоде.
     */
    void createWeather(Weather weather);
}
