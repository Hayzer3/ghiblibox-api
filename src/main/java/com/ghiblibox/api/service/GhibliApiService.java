package com.ghiblibox.api.service;

import com.ghiblibox.api.dto.FilmeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GhibliApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://ghibliapi.vercel.app/films";

    public List<FilmeDTO> listarTodosOsFilmes() {
        FilmeDTO[] filmes = restTemplate.getForObject(apiUrl, FilmeDTO[].class);
        return filmes != null ? Arrays.asList(filmes) : List.of();
    }

    public FilmeDTO buscarFilmePorId(String id) {
        String url = apiUrl + "/" + id;
        return restTemplate.getForObject(url, FilmeDTO.class);
    }
}