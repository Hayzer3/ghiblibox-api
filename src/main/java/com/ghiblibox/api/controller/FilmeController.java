package com.ghiblibox.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @GetMapping
    public String testarAPI() {
        return "API do Ghibli Box a funcionar perfeitamente!";
    }
}