package ru.kuryakin.meteo.collector.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Sensors {

    List<String> urls;

}
