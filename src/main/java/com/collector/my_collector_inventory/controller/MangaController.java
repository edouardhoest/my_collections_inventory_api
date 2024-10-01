package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.entities.MangaEntity;
import com.collector.my_collector_inventory.services.MangaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/{idManga}")
    public MangaEntity getSingleManga(@PathVariable Integer idManga) throws IOException {
        return mangaService.findById(idManga.longValue());
    }
}
