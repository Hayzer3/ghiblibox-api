package com.ghiblibox.api.controller;

import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/{id}/seguir")
    public ResponseEntity alternarSeguir(@PathVariable Integer id) {

        // pega usuario logado pelo token do spring security
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.alternarSeguir(id, usuarioLogado);

        return ResponseEntity.ok().build();
    }
}