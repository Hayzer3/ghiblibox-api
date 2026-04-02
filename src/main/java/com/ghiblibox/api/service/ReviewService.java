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

    @Transactional // A rede de segurança!
    public void salvarReviewEAtualizarUsuario(Review novaReview, Usuario usuarioLogado) {
        // 1. Salva a nova review no banco de dados
        reviewRepository.save(novaReview);

        // 2. Chama aquele método que criamos para aumentar o número (+1)
        usuarioLogado.adicionarAvaliacao();

        // 3. Salva o usuário atualizado no banco
        usuarioRepository.save(usuarioLogado);
    }
}