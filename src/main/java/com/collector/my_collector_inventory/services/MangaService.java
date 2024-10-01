package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.entities.MangaEntity;
import com.collector.my_collector_inventory.repositories.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {
    @Autowired
    private MangaRepository mangaRepository;

    public MangaEntity save(MangaEntity mangaEntity) {
        return mangaRepository.save(mangaEntity);
    }

    public List<MangaEntity> findAll() {
        return mangaRepository.findAll();
    }

    public MangaEntity findById(Long id) {
        return mangaRepository.findById(id).orElse(null);
    }

    public MangaEntity update(MangaEntity mangaEntity) {
        return mangaRepository.save(mangaEntity);
    }

    public MangaEntity findByTitle(String title) {
        return mangaRepository.findMangaEntitiesByTitle(title);
    }

    public void deleteById(Long id) {
        mangaRepository.deleteById(id);
    }
}
