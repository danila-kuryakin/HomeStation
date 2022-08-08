package ru.kuryakin.meteo.collector.service;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.kuryakin.meteo.collector.repository.WeatherRepository;
import ru.kuryakin.meteo.collector.models.Weather;

import java.util.GregorianCalendar;

/***
 * Реализация интерфейса WeatherDAO.
 */
@Service
@AllArgsConstructor
public class WeatherService implements WeatherRepository {


    private JdbcTemplate jdbcTemplate;

    /***
     * Добавление данных о погоде в базу данных
     * @param weather - данные о погоде.
     */
    public void createWeather(Weather weather) {
        String sql = "insert into weather (temperature, humidity, pressure, date, location) values(?, ?, ?, ?, ?);";
        GregorianCalendar date = new GregorianCalendar();
        jdbcTemplate.update(sql, weather.getTemperature(), weather.getHumidity(), weather.getPressure(),
                date, weather.getLocation().getId());
    }
}
