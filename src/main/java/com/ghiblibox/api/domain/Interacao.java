package com.ghiblibox.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_interacoes")
@Entity(name = "Interacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idInteracao")
public class Interacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInteracao;

    // id vem da api do ghibli
    @Column(name = "api_filme_id", nullable = false)
    private String apiFilmeId;

    // true se ele já assistiu, false se não
    @Column(name = "assistido")
    private Boolean assistido = false;

    @Column(name = "favorito")
    private Boolean favorito = false;

    // varias interações podem pertencer a um único usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}