package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.repositories.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {
    @Autowired
    private MangaRepository mangaRepository;

    public Manga save(Manga manga) {
        return mangaRepository.save(manga);
    }

    public List<Manga> findAll() {
        return mangaRepository.findAll();
    }

    public Manga findById(Long id) {
        return mangaRepository.findById(id).orElse(null);
    }

    public Manga update(Manga manga) {
        return mangaRepository.save(manga);
    }

    public Manga findByTitle(String title) {
        return mangaRepository.findByTitle(title);
    }


    public void deleteById(Long id) {
        mangaRepository.deleteById(id);
    }
}
