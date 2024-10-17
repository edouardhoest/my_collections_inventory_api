package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.dto.ListMangaDTO;
import com.collector.my_collector_inventory.dto.MangaData;
import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.repositories.MangaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

@Slf4j
@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;
    private final ObjectMapper mapper = new ObjectMapper();


    public Manga findById(Long id) {
        return mangaRepository.findById(id).orElse(null);
    }

    public List<Manga> findTopTen(){
        return mangaRepository.findTopTen();
    }

    public void saveManga(Response response) throws IOException {
        if (response.body() != null) {
            ListMangaDTO mangas = mapper.readValue(response.body().byteStream(), ListMangaDTO.class);
            List<Manga> formattedMangaList = mangas.getData().stream().map(
                    MangaService::mangaDataToMangaMapper
            ).toList();
            mangaRepository.saveAll(formattedMangaList);
        }
    }

    private static Manga mangaDataToMangaMapper(MangaData data) {
        return Manga.builder()
                .title(data.title)
                .authors(data.authors != null ? data.authors[0].name : "Manga as no author")
                .description(data.synopsis)
                .imageUrl(data.images.getJpg().imageUrl)
                .categories(data.genres != null ? data.genres[0].name : "Manga as no categories")
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
