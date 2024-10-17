package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.repositories.MangaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    @Transactional
    public Manga save(Manga manga) {
        return mangaRepository.save(manga);
    }

    @Transactional
    public void saveAll(List<Manga> mangas) {
        mangaRepository.saveAll(mangas);
    }

    public List<Manga> findAll() {
        return mangaRepository.findAll();
    }

    public Manga findById(Long id) {
        return mangaRepository.findById(id).orElse(null);
    }

    public void update(Manga manga) {
        mangaRepository.save(manga);
    }

    public Manga findByTitle(String title) {
        return mangaRepository.findByTitle(title);
    }

    public void deleteById(Long id) {
        mangaRepository.deleteById(id);
    }
}
