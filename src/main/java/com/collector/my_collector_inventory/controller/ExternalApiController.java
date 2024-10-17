package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.dto.ListMangaDTO;
import com.collector.my_collector_inventory.dto.MangaDTO;
import com.collector.my_collector_inventory.dto.MangaData;
import com.collector.my_collector_inventory.services.MangaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/externalManga")
public class ExternalApiController {

    @Autowired
    public MangaService mangaService;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String EXTERNAL_API_URL = "https://api.jikan.moe/v4/manga";


    @GetMapping("")
    public ListMangaDTO getMangaList() throws IOException {
        Request request = new Request.Builder()
                .url(EXTERNAL_API_URL)
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

    @GetMapping("/load-manga")
    public void loadManga() throws IOException {
        Request request = new Request.Builder()
                .url(EXTERNAL_API_URL)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            mangaService.saveManga(response);
        }
    }

    @GetMapping("/id/{idManga}")
    public MangaDTO getSingleManga(@PathVariable Integer idManga) throws IOException {
        Request request = new Request.Builder()
                .url(EXTERNAL_API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            if (response.body() != null) {
                return mapper.readValue(response.body().byteStream(), MangaDTO.class);
            } else {
                return new MangaDTO(new MangaData());
            }
        }
    }
    //TODO get manga by name
}