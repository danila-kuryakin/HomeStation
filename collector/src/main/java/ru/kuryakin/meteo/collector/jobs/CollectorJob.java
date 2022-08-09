package ru.kuryakin.meteo.collector.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kuryakin.meteo.collector.models.Sensors;
import ru.kuryakin.meteo.collector.service.WeatherService;
import ru.kuryakin.meteo.collector.models.Location;

import java.io.IOException;
import java.time.LocalDateTime;

/***
 * Сбор данных с метеостанций в определенное время.
 */
@Component
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class CollectorJob {

    private static final String COLLECT_ERROR_FORM = "Collector don't get weather data at the ip(%s)";
    private static final String USER_AGENT = "Chrome/4.0.249.0 Safari/532.5";
    private static final String START_COLLECTOR = "Start collector";

    /***
     * Список устройств с датчиками, которые ныжно опросить.
     */
    private Sensors sensors;

    /***
     * Шаблон с sql запросами.
     */
    private WeatherService weatherDAO;

    /***
     * Запуск сборщика данных.
     */
    @Scheduled(cron="0 0/2 * * * *")
    public void collect() {

        log.info(START_COLLECTOR);
        for (String url:this.sensors.getUrls()) {
            if (!getWeatherFromController(url))
                log.error(String.format(COLLECT_ERROR_FORM, url));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("%s", e.getMessage());

            }
        }
    }

    /***
     * Считывание и добавление в БД данных с датчика.
     * @param url - адрес устройства.
     * @return подтверждение считывание и добавление в БД данных с датчика.
     */
    private boolean getWeatherFromController(String url) {
        JSONParser parser = new JSONParser();
        Double temperature, humidity, pressure;
        Location location = null;

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .get();
            Elements listNews = doc.select("body");

            JSONObject jsonSensorsData = (JSONObject) parser.parse(listNews.text());

            temperature= getInstance(jsonSensorsData, "Temperature");
            humidity = getInstance(jsonSensorsData, "Humidity");
            pressure = getInstance(jsonSensorsData, "Pressure");


            Object locationObject = jsonSensorsData.get("Location");
            if (locationObject instanceof String)
                location = Location.valueOf(String.valueOf(locationObject));

            this.weatherDAO.addData(temperature, humidity, pressure, location);
            return true;
        }catch (IOException | ParseException e){
            log.error(e.getMessage());
        }
        return false;
    }

    /***
     * Приведение информации с json в тип Double
     * @param jsonSensorsData
     * @param sensorName
     * @return
     */
    private Double getInstance(JSONObject jsonSensorsData, String sensorName) {
        Object tempObject = jsonSensorsData.get(sensorName);
        if (tempObject instanceof Double)
            return (Double) tempObject;
        else
            return null;
    }

}

