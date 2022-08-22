package ru.kuryakin.meteo.web.constant;

import java.time.format.DateTimeFormatter;

public class Constants {
    /***
     * Формат вывода.
     */
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM HH:mm:ss");
    public static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
}
