package com.ghiblibox.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroReview(

        @NotBlank
        String apiFilmeId,

        @NotNull
        @Min(1)
        @Max(5)
        Integer nota,

        String texto
) {}