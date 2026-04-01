package com.ghiblibox.api.controller;

import com.ghiblibox.api.domain.Review;
import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.dto.DadosCadastroReview;
import com.ghiblibox.api.dto.DadosListagemReview;
import com.ghiblibox.api.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroReview dados) {

        //  Descobre quem é o usuário logado através do Token
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Monta a nova Review com os dados que vieram do React e o usuário logado
        Review novaReview = new Review();
        novaReview.setApiFilmeId(dados.apiFilmeId());
        novaReview.setNota(dados.nota());
        novaReview.setTexto(dados.texto());
        novaReview.setUsuario(usuarioLogado);

        //  Salva a review no MariaDB
        repository.save(novaReview);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/filme/{apiFilmeId}")
    public ResponseEntity<List<DadosListagemReview>> listarPorFilme(@PathVariable String apiFilmeId) {
        var reviews = repository.findAllByApiFilmeId(apiFilmeId)
                .stream()
                .map(DadosListagemReview::new)
                .toList();

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DadosListagemReview>> listarPorUsuario(@PathVariable Integer usuarioId) {

        var reviews = repository.findAllByUsuario_IdUsuario(usuarioId)
                .stream()
                .map(DadosListagemReview::new)
                .toList();

        return ResponseEntity.ok(reviews);
    }

}