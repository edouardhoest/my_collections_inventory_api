package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.dto.Data;
import com.collector.my_collector_inventory.dto.ListMangaDTO;
import com.collector.my_collector_inventory.dto.MangaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/manga")
public class ExternalApiController {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("")
    public ListMangaDTO getMangaList() throws IOException {
        String url = "https://api.jikan.moe/v4/manga";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            if (response.body() != null) {
                ListMangaDTO listMangaDTO = mapper.readValue(response.body().byteStream(), ListMangaDTO.class);

                return listMangaDTO;
            } else {
                return new ListMangaDTO();
            }
        }
    }

    @GetMapping("/{idManga}")
    public MangaDTO getSingleManga(@PathVariable Integer idManga) throws IOException {
        String url = "https://api.jikan.moe/v4/manga/" + idManga;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            if (response.body() != null) {
                return mapper.readValue(response.body().byteStream(), MangaDTO.class);
            } else {
                return new MangaDTO(new Data());
            }
        }
    }

    @GetMapping("/{name}")
    public MangaDTO getSingleManga(@PathVariable String name) throws IOException {
        // appel bdd pour recup id
        int idManga = 1;//MangaRepository.getMangaByName(name);
        String url = "https://api.jikan.moe/v4/manga/" + idManga;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            if (response.body() != null) {
                return mapper.readValue(response.body().byteStream(), MangaDTO.class);
            } else {
                return new MangaDTO(new Data());
            }
        }
    }

}