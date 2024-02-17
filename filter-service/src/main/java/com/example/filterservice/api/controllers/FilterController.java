package com.example.filterservice.api.controllers;

import com.example.filterservice.business.abstracts.FilterService;
import com.example.filterservice.business.dto.responses.GetAllFilterResponse;
import com.example.filterservice.entities.Filter;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
public class FilterController {
    private final FilterService service;

    /*@PostConstruct
    public void createDb(){
        service.add(new Filter());
    }*/

    @GetMapping
    List<GetAllFilterResponse> getAll(){
        return service.getAll();
    }
}
