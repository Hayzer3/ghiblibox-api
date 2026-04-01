package com.ghiblibox.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_reviews")
@Entity(name = "Review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String texto;

    private String apiFilmeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}