package com.ghiblibox.api.dto;

import com.ghiblibox.api.domain.Usuario;

public record UsuarioSimplesDTO(
        Integer id,
        String username
        // colocar ft no futuro
) {
    // construtor para convertar entidade em dto
    public UsuarioSimplesDTO(Usuario usuario) {
        this(usuario.getIdUsuario(), usuario.getUsername());
    }
}