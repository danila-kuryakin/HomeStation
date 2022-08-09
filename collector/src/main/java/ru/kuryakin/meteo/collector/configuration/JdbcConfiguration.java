package ru.kuryakin.meteo.collector.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class JdbcConfiguration {

    /***
     * Чтение настроек из файла и подключение к БД.
     * Состав файла: database.txt
     *     Строка 1 - Driver Class Name
     *     Строка 2 - Url
     *     Строка 3 - Username
     *     Строка 4 - Password
     * @return Подключение к БД. В случае ошибки null.
     */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try(FileReader fr = new FileReader("database.txt"))
        {
            BufferedReader reader = new BufferedReader(fr);

            dataSource.setDriverClassName(reader.readLine());
            dataSource.setUrl(reader.readLine());
            dataSource.setUsername(reader.readLine());
            dataSource.setPassword(reader.readLine());
            return dataSource;
        }
        catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
