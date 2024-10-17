package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.services.MangaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping("/{idManga}")
    public ResponseEntity<Manga> getSingleManga(@PathVariable Long idManga, HttpServletRequest request) {
        Manga manga = mangaService.findById(idManga);
        if (manga != null) {
            return ResponseEntity.ok(manga);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/top")
    public void getTopTenManga() {
        //TODO List<Manga> topManga = mangaService.getTopTen();
        //TODO return ResponseEntity.ok(topManga);
    }

    @GetMapping("/{idManga}/image")
    public ResponseEntity<byte[]> getMangaImage(@PathVariable Long idManga) throws IOException {
        Manga manga = mangaService.findById(idManga);
        if (manga != null && manga.getImageUrl() != null) {
            byte[] mangaBytesImage = mangaService.getToBytesImage(manga.getImageUrl());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(mangaBytesImage, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}