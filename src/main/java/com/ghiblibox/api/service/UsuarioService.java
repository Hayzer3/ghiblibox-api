package com.ghiblibox.api.service;

import com.ghiblibox.api.domain.Usuario;
import com.ghiblibox.api.dto.UsuarioSimplesDTO;
import com.ghiblibox.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public void alternarSeguir(Integer idAlvo, Usuario usuarioLogadoDoToken) {

        var usuarioAlvo = repository.findById(idAlvo)
                .orElseThrow(() -> new RuntimeException("usuário alvo não encontrado"));

        // busca o usuario logado no banco usando o ID que veio do token
        var usuarioLogado = repository.findById(usuarioLogadoDoToken.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("usuário logado não encontrado no banco"));

        // impede o usuario de seguir a si mesmo
        if (usuarioLogado.getIdUsuario().equals(usuarioAlvo.getIdUsuario())) {
            throw new RuntimeException("não é permitido seguir o próprio perfil");
        }

        // verifica se ja esta na lista para decidir se adiciona ou remove
        if (usuarioLogado.getSeguindoLista().contains(usuarioAlvo)) {
            usuarioLogado.deixarDeSeguir(usuarioAlvo);
        } else {
            usuarioLogado.seguir(usuarioAlvo);
        }

        repository.save(usuarioLogado);
        repository.save(usuarioAlvo);
    }

    // lista de quem segue o usuario procurado
    @Transactional(readOnly = true)
    public List<UsuarioSimplesDTO> listarSeguidores(Integer idUsuario) {
        var usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario.getSeguidoresLista().stream()
                .map(UsuarioSimplesDTO::new)
                .toList();
    }

    // lista de quem o usuário procurado esta seguindo
    @Transactional(readOnly = true)
    public List<UsuarioSimplesDTO> listarSeguindo(Integer idUsuario) {
        var usuario = repository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario.getSeguindoLista().stream()
                .map(UsuarioSimplesDTO::new)
                .toList();
    }
}