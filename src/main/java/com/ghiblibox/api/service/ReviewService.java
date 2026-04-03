package com.ghiblibox.api.service;

import com.ghiblibox.api.domain.Review;
import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.repository.ReviewRepository;
import com.ghiblibox.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void salvarReviewEAtualizarUsuario(Review novaReview, Usuario usuarioLogado) {
        // salva a nova review no banco
        reviewRepository.save(novaReview);

        // metodo aumentar o número (+1)
        usuarioLogado.adicionarAvaliacao();

        usuarioRepository.save(usuarioLogado);
    }
}