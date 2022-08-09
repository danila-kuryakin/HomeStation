package ru.kuryakin.meteo.collector.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/***
 * Данные с датчиков.
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private LocalDateTime date;

    @Column
    private Double temperature;

    @Column
    private Double humidity;

    @Column
    private Double pressure;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Location location;

}
