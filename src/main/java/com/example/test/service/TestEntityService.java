package com.example.test.service;

import com.example.test.entity.TestEntity;
import com.example.test.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestEntityService {
    private final TestEntityRepository testEntityRepository;

    @Autowired
    public TestEntityService(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    public Page<TestEntity> getEntities(String query, Pageable pageable) {
        if (query == null || query.isEmpty()) {
            return testEntityRepository.findAllByOrderByNameAscUrlAsc(pageable);
        }
        return testEntityRepository.findByNameContainingIgnoreCase(query, pageable);
    }
}
