package ru.kuryakin.meteo.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class CollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectorApplication.class, args);
//		ApplicationContext context = new AnnotationConfigApplicationContext(CollectorApplication.class);
//
//		TimerRunner timerRunner = context.getBean(TimerRunner.class);
//		timerRunner.run();
//		while (true){
//			Scanner in = new Scanner(System.in);
//			String str = in.next();
//			if (str.equals("stop")){
//				return;
//			}
//		}
	}

}
