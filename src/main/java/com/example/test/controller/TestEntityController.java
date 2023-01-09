package com.example.test.controller;

import com.example.test.entity.TestEntity;
import com.example.test.service.TestEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestEntityController {

    private final TestEntityService testEntityService;

    @Autowired
    public TestEntityController(TestEntityService entityService) {
        this.testEntityService = entityService;
    }

    @GetMapping("/")
    public String getEntities(@RequestParam(required = false) String query,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "5") int size, Model model) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<TestEntity> testEntityPage = testEntityService.getEntities(query, paging);

        model.addAttribute("query", query);
        model.addAttribute("entities", testEntityPage.getContent());
        model.addAttribute("currentPage", testEntityPage.getNumber() + 1);
        model.addAttribute("totalPages", testEntityPage.getTotalPages());
        model.addAttribute("totalItems", testEntityPage.getTotalElements());
        model.addAttribute("pageSize", size);

        return "entities";
    }
}
