package ru.kuryakin.meteo.web.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.web.models.Location;
import ru.kuryakin.meteo.web.models.Weather;
import ru.kuryakin.meteo.web.repository.WebWeatherRepository;

import java.util.List;

@Component
public class WeatherService {

    /**
     * Репозиторий.
     */
    @Autowired
    public WebWeatherRepository webWeatherRepository;

    /**
     * Вернуть все значения.
     * @return Список значений.
     */
    public List<Weather> findAll() {
        return webWeatherRepository.findAll();
    }

    /***
     * Возвращает 10 последних строк из таблицы.
     * @return список со значениями с датчиков.
     */
    public List<Weather> findTop10ByOrderByIdDesc(){
        return webWeatherRepository.findTop10ByOrderByIdDesc();
    }

    /***
     * Возвращает последние строку по местоположению из таблицы.
     * @param location - местоположение.
     * @return значение с датчиков.
     */
    public Weather findByLastWeather(Location location){
        return webWeatherRepository.findTopByLocationOrderByIdDesc(location);
    }

    /***
     * Возвращает N последних строк по местоположению из таблицы.
     * @param location - местоположение.
     * @return список со значениями с датчиков.
     */
    public List<Weather> findNCurrent(Location location, int size){
        return webWeatherRepository.findAllByLocationOrderByIdDesc(location, PageRequest.of(1, size));
    }
}
