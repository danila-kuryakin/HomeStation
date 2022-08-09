package ru.kuryakin.meteo.collector.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuryakin.meteo.collector.models.Location;
import ru.kuryakin.meteo.collector.repository.WeatherRepository;
import ru.kuryakin.meteo.collector.models.Weather;

import java.time.LocalDateTime;

/***
 * Реализация интерфейса WeatherDAO.
 */
@Service
@AllArgsConstructor
public class WeatherService {


    private final WeatherRepository weatherRepository;

    /***
     * Добавление данных о погоде в базу данных
     * @param temperature - температура.
     * @param humidity - влажность.
     * @param pressure - давление.
     * @param location - местополжение.
     */
    public void addData(Double temperature, Double humidity, Double pressure, Location location) {
        LocalDateTime date = LocalDateTime.now();
        Weather weather = new Weather(0, date, temperature, humidity, pressure, location);
        weatherRepository.save(weather);
    }
}
