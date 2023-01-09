package com.example.test.csv;

import com.example.test.entity.TestEntity;
import com.example.test.repository.TestEntityRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvEntityLoader {

    private final TestEntityRepository testEntityRepository;

    @Autowired
    public CsvEntityLoader(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    public void loadCsvEntitiesFromFile(String filename) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Invalid csv file name: " + filename);
        }

        // parse CSV file to get a list of TestEntity objects
        try (FileInputStream fis = new FileInputStream(filename);
             InputStreamReader isr = new InputStreamReader(fis);
             Reader reader = new BufferedReader(isr)) {

            // create csv bean reader
            CsvToBean<TestEntity> csvToBean = new CsvToBeanBuilder<TestEntity>(reader)
                    .withType(TestEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<TestEntity> list = csvToBean.parse();
            testEntityRepository.saveAll(list);
        }
    }
}
