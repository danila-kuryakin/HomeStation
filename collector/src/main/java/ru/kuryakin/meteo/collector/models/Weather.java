package ru.kuryakin.meteo.collector.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/***
 * Данные о погоде.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wather")
public class Weather {

    /***
     * Формат вывода даты.
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm:ss");

    @Id
    @Column
    private long id;

    @Column
    private GregorianCalendar date;

    @Column
    private Double temperature;

    @Column
    private Double humidity;

    @Column
    private Double pressure;

    @Column
    private Location location;

}
