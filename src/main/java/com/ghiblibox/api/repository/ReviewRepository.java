package com.ghiblibox.api.repository;

import com.ghiblibox.api.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByApiFilmeId(String apiFilmeId);

    // Atualizado com o nome correto da propriedade (IdUsuario) e tipo (Integer)
    List<Review> findAllByUsuario_IdUsuario(Integer idUsuario);
}