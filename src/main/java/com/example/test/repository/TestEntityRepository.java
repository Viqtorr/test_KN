package com.example.test.repository;

import com.example.test.entity.TestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
    Page<TestEntity> findByNameContainingIgnoreCase(String query, Pageable pageable);

    Page<TestEntity> findAllByOrderByNameAscUrlAsc(Pageable pageable);
}
