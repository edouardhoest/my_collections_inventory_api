package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.dto.Author;
import com.collector.my_collector_inventory.dto.Genre;
import com.collector.my_collector_inventory.dto.ListMangaDTO;
import com.collector.my_collector_inventory.dto.MangaData;
import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.repositories.MangaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String EXTERNAL_API_URL = "https://api.jikan.moe/v4/manga";

    public List<Manga> findAllMangas() {
        return mangaRepository.findAll();
    }

    public Manga findById(Long id) {
        return mangaRepository.findById(id).orElse(null);
    }

    public List<Manga> findAllById(Long id) {
        return mangaRepository.findAllById(Collections.singleton(id));
    }

    public List<Manga> findTopTen() {
        return mangaRepository.findTopTen();
    }

    public void saveManga() {
        ListMangaDTO mangas = null;
        int i = 1;
        do {
            Request request = new Request.Builder()
                    .url(EXTERNAL_API_URL + "?page=" + i)
                    .build();
            i++;
            try (Response response = client.newCall(request).execute()) {
                Thread.sleep(1000);
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                if (response.body() != null) {
                    mangas = mapper.readValue(response.body().byteStream(), ListMangaDTO.class);
                    List<Manga> formattedMangaList = mangas.getData().stream().map(
                            MangaService::mangaDataToMangaMapper
                    ).toList();
                    mangaRepository.saveAll(formattedMangaList);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (i > 10); // Objects.requireNonNull(mangas).getPagination().hasNextPage
    }

    private static Manga mangaDataToMangaMapper(MangaData data) {
        return Manga.builder()
                .title(data.title)
                .authors(data.authors != null ? Arrays.stream(data.authors).map(Author::getName).findAny().orElse("Manga as no authors") : "Manga as no author")
                .description(data.synopsis)
                .imageUrl(data.images.getJpg().imageUrl)
                .categories(data.genres != null ? Arrays.stream(data.genres).map(Genre::getName).findAny().orElse("Manga as no categories") : "Manga as no categories")
                .status(data.status)
                .build();
    }

    public byte[] getToBytesImage(String imageUrl) throws IOException {
        URL url = URI.create(imageUrl).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        try (InputStream inputStream = connection.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        }
    }
}
