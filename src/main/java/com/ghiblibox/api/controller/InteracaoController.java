package com.ghiblibox.api.controller;

import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.dto.DadosToggleInteracao;
import com.ghiblibox.api.service.InteracaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interacoes")
@CrossOrigin("*")
public class InteracaoController {

    @Autowired
    private InteracaoService service;

    @PostMapping
    public ResponseEntity registrarInteracao(@RequestBody @Valid DadosToggleInteracao dados) {

        // descobre o usuario logado pelo token
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // envia para o service processar a regra de negocio
        service.processarInteracao(dados, usuarioLogado);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<String>> listarFavoritos() {

        // pega o usuario logado pelo token
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // pede pro service a lista de ids favoritos
        var favoritos = service.listarFilmesFavoritos(usuarioLogado);

        return ResponseEntity.ok(favoritos);
    }
}