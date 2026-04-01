package com.ghiblibox.api.controller;

import com.ghiblibox.api.dto.FilmeDTO;
import com.ghiblibox.api.service.GhibliApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@CrossOrigin("*")
public class FilmeController {

    @Autowired
    private GhibliApiService service;

    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarFilmes() {
        List<FilmeDTO> filmes = service.listarTodosOsFilmes();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarFilme(@PathVariable String id) {
        FilmeDTO filme = service.buscarFilmePorId(id);

        if (filme != null) {
            return ResponseEntity.ok(filme);
        }

        return ResponseEntity.notFound().build();
    }
}