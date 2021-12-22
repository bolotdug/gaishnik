package com.inovus.gaishnik;

import com.inovus.gaishnik.model.CarNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@SpringBootApplication
public class GaishnikApplication {
    public static void main(String[] args) {
        SpringApplication.run(GaishnikApplication.class, args);
    }
}
