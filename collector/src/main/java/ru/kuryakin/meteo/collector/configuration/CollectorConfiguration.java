package ru.kuryakin.meteo.collector.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kuryakin.meteo.collector.models.Sensors;
import ru.kuryakin.meteo.collector.provider.ConstantsProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class CollectorConfiguration {

    private ConstantsProvider constantsProvider;

    /***
     * Загрузка прочинааный url. Либо с файла (если constantsProvider.getIsRead() == true),
     * либо с аргументов (если constantsProvider.getIsRead() == false).
     * @return Класс со списком сенсоров.
     */
    @Bean
    public Sensors sensors() {
        if (constantsProvider.getIsRead()) {
            List<String> urls = new ArrayList<>();
            try (FileReader fr = new FileReader(constantsProvider.getFileName())) {
                BufferedReader reader = new BufferedReader(fr);

                String line = reader.readLine();
                while (line != null) {
                    urls.add(line);
                    line = reader.readLine();
                }
                return new Sensors(urls);
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return new Sensors(constantsProvider.getUrls());
        }
    }
}
