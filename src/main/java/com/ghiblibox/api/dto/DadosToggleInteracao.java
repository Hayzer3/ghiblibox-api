package com.ghiblibox.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosToggleInteracao(
        @NotBlank
        String apiFilmeId,

        @NotNull
        Boolean assistido,

        @NotNull
        Boolean favorito
) {
}