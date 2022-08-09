package ru.kuryakin.meteo.collector.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kuryakin.meteo.collector.models.Sensors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class CollectorConfiguration {

    private static final String FILE_NAME = "stations.txt";

    // todo: написать считыванеи имя файла с аргументов, при запуске программы.
    @Bean
    public Sensors sensors() {
        List<String> urls = new ArrayList<>();
        try(FileReader fr = new FileReader(FILE_NAME))
        {
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                urls.add(line);
                line = reader.readLine();
            }
            return new Sensors(urls);
        }
        catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
