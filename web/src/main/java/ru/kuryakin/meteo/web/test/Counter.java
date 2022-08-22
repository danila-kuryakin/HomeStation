package ru.kuryakin.meteo.web.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Getter
public class Counter {
    private int counter;
    private CounterHistory counterHistory;

    public Counter() {
        this.counter = 0;
        this.counterHistory = new CounterHistory();
    }

    public void upCounter(Integer value){
        this.counter += value;
        this.counterHistory.add(this.counter);
    }

    public void downCounter(Integer value){
        this.counter -= value;
        this.counterHistory.add(this.counter);
    }
}
