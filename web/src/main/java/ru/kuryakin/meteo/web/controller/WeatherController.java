package ru.kuryakin.meteo.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kuryakin.meteo.web.constant.Constants;
import ru.kuryakin.meteo.web.models.Location;
import ru.kuryakin.meteo.web.models.Page;
import ru.kuryakin.meteo.web.models.Weather;
import ru.kuryakin.meteo.web.servise.WeatherService;
import ru.kuryakin.meteo.web.test.Counter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class WeatherController{

    private WeatherService weatherService;
    private Counter counter;

    @GetMapping({ "/weather"})
    public String getAllRecords(Model model){
        List<Weather> lastLocalCurrents = new ArrayList();
        for (Location location:Location.values()) {
            lastLocalCurrents.add(weatherService.findByLastWeather(location));
        }

        model.addAttribute("locations", lastLocalCurrents);
        model.addAttribute("weathers", weatherService.findTop10ByOrderByIdDesc());
        model.addAttribute("greetings", counter.getCounterHistory());
        model.addAttribute("counter", counter.getCounter());
        model.addAttribute("humidityVisible", true);
        model.addAttribute("pressureVisible", true);
        return "weather/index";
    }

    @GetMapping("/weather/{name}")
    public String show(@PathVariable("name") String name, Model model) {
        List<Weather> lastLocalCurrents = new ArrayList();
        for (Location location:Location.values()) {
            lastLocalCurrents.add(weatherService.findByLastWeather(location));
        }
        model.addAttribute("locations", lastLocalCurrents);

        List<Weather> top = weatherService.findNCurrent(Location.valueOf(name), 30);
        List<String> date = new ArrayList<>();
        List<Double> temperature = new ArrayList<>();
        List<Double> humidity = new ArrayList<>();
        List<Double> pressure = new ArrayList<>();

        boolean notNullHum = false, notNullPres = false;
        for (int i = top.size()-1; i >= 0; i--) {
            date.add(top.get(i).getDate().format(Constants.dateFormat));
            temperature.add(top.get(i).getTemperature());
            humidity.add(top.get(i).getHumidity());
            if (top.get(i).getHumidity() != null) notNullHum = true;
            pressure.add(top.get(i).getPressure());
            if (top.get(i).getPressure() != null) notNullPres = true;
        }

        model.addAttribute("date", date);
        model.addAttribute("temperature", temperature);
        model.addAttribute("humidityVisible", notNullHum);
        if (notNullHum) model.addAttribute("humidity", humidity);
        model.addAttribute("pressureVisible", notNullPres);
        if (notNullPres) model.addAttribute("pressure", pressure);
        model.addAttribute("last", top.get(0));
        model.addAttribute("weathers", top);
        return "weather/status";
    }
}

