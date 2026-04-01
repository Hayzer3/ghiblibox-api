package com.ghiblibox.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilmeDTO(

        String id,
        String title,
        @JsonProperty("original_title") String originalTitle,
        @JsonProperty("original_title_romanised") String originalTitleRomanised,
        String description,
        String director,
        String producer,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("running_time") String runningTime,
        @JsonProperty("rt_score") String rtScore,
        String image,
        @JsonProperty("movie_banner") String movieBanner

) {}
