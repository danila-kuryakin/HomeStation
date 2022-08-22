package ru.kuryakin.meteo.collector.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class ConstantsProvider {

    @Value("${stations-path-active}")
    private Boolean isRead;

    @Value("${stations-path}")
    private String fileName;

    @Value("${stations}")
    private List<String> urls;
}
