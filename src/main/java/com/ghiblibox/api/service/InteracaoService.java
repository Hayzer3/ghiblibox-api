package com.ghiblibox.api.service;

import com.ghiblibox.api.domain.Interacao;
import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.dto.DadosToggleInteracao;
import com.ghiblibox.api.repository.InteracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InteracaoService {

    @Autowired
    private InteracaoRepository repository;

    @Transactional
    public void processarInteracao(DadosToggleInteracao dados, Usuario usuarioLogado) {

        // busca se o usuario ja interagiu com este filme antes
        var interacaoExistente = repository.findByUsuarioAndApiFilmeId(usuarioLogado, dados.apiFilmeId());

        if (interacaoExistente.isPresent()) {

            // atualiza a interacao que ja existe no banco
            var interacao = interacaoExistente.get();
            interacao.setAssistido(dados.assistido());
            interacao.setFavorito(dados.favorito());
            repository.save(interacao);
        } else {

            // cria uma nova interacao do zero
            var novaInteracao = new Interacao();
            novaInteracao.setApiFilmeId(dados.apiFilmeId());
            novaInteracao.setAssistido(dados.assistido());
            novaInteracao.setFavorito(dados.favorito());
            novaInteracao.setUsuario(usuarioLogado);
            repository.save(novaInteracao);
        }

    }

    // retorna apenas uma lista com os ids dos filmes que o usuario favoritou
    public List<String> listarFilmesFavoritos(Usuario usuarioLogado) {
        return repository.findAllByUsuarioAndFavoritoTrue(usuarioLogado)
                .stream()
                .map(Interacao::getApiFilmeId)
                .toList();
    }
}