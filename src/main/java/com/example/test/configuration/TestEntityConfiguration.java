package com.example.test.configuration;

import com.example.test.csv.CsvEntityLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestEntityConfiguration {
    @Bean
    CommandLineRunner commandLineRunnerCsv(CsvEntityLoader csvEntityLoader) {
        // load some entities for testing purposes...
        return args -> csvEntityLoader.loadCsvEntitiesFromFile("people.csv");
    }
}
