package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.dto.MangaData;
import com.collector.my_collector_inventory.dto.ListMangaDTO;
import com.collector.my_collector_inventory.dto.MangaDTO;
import com.collector.my_collector_inventory.entities.Collection;
import com.collector.my_collector_inventory.entities.Manga;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

            if (response.body() != null) {
                ListMangaDTO mangas = mapper.readValue(response.body().byteStream(), ListMangaDTO.class);
                List<Manga> formattedMangaList = mangas.getData().stream().map(
                        data -> Manga.builder()
                                .title(data.title)
                                .authors(data.authors != null ? data.authors[0].name : "Manga as no author")
                                .description(data.synopsis)
                                .imageUrl(data.images.getJpg().imageUrl)
                                .categories(data.genres != null ? data.genres[0].name : "Manga as no categories")
                                .status(data.status)
                                .build()
                ).toList();
                mangaService.saveAll(formattedMangaList);
            }
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

    @GetMapping("/{name}")
    public MangaDTO getSingleManga(@PathVariable String name) throws IOException {
        // appel bdd pour recup id
        int idManga = 1;//MangaRepository.getMangaByName(name);

        Request request = new Request.Builder()
                .url(EXTERNAL_API_URL + "/" + idManga)
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

}