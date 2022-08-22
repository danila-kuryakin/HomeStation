package ru.kuryakin.meteo.web.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
@AllArgsConstructor
public class CounterHistory {
    List<Integer> history;

    public CounterHistory() {
        this.history = new ArrayList<>(Arrays.asList(0));
    }

    @Override
    public String toString() {
        String returnStr = "";

        for (int i = 0; i < history.size(); i++) {
            if (i == history.size()-1)
                returnStr += history.get(i);
            else
                returnStr += history.get(i) + " -> ";
        }

        return returnStr;
    }

    public void add(Integer value) {
        this.history.add(value);
    }
}
