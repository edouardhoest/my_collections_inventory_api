package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping("/{idManga}")
    public ResponseEntity<Manga> getSingleManga(@PathVariable Long idManga) {
        Manga manga = mangaService.findById(idManga);
        if (manga != null) {
            return ResponseEntity.ok(manga);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/top")
    public void getTopTenManga() {
        // TODO List<Manga> topManga = mangaService.getTopTen();
        //TODO return ResponseEntity.ok(topManga);
    }
}