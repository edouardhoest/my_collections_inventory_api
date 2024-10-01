package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.dto.Data;
import com.collector.my_collector_inventory.dto.MangaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExternalApiController {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/manga")
    public MangaDTO getMangaList() throws IOException {
        String url = "https://api.jikan.moe/v4/manga";

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
                return new MangaDTO(new Data[0], null);
            }
        }
    }
}