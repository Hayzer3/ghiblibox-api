package com.ghiblibox.api.dto;

import com.ghiblibox.api.domain.Review;

public record DadosListagemReview(
        Long id,
        String apiFilmeId,
        Integer nota,
        String texto,
        Integer usuarioId, // Mudamos de Long para Integer aqui!
        String username
) {
    public DadosListagemReview(Review review) {
        this(
                review.getId(),
                review.getApiFilmeId(),
                review.getNota(),
                review.getTexto(),
                review.getUsuario().getIdUsuario(),
                review.getUsuario().getUsername() // retorna email
        );
    }
}