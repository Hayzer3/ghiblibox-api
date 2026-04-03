package com.ghiblibox.api.repository;

import com.ghiblibox.api.domain.Interacao;
import com.ghiblibox.api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InteracaoRepository extends JpaRepository<Interacao, Long> {

    // busca a interacao especifica de um usuario com um filme
    Optional<Interacao> findByUsuarioAndApiFilmeId(Usuario usuario, String apiFilmeId);
    // busca todas as interacoes onde o usuario favoritou o filme
    List<Interacao> findAllByUsuarioAndFavoritoTrue(Usuario usuario);
}