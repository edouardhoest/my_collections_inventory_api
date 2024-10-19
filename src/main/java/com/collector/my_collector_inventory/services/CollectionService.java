package com.collector.my_collector_inventory.services;

import com.collector.my_collector_inventory.dto.CollectionDTO;
import com.collector.my_collector_inventory.entities.Collection;
import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.repositories.CollectionRepository;
import com.collector.my_collector_inventory.repositories.MangaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private MangaRepository mangaRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Manga> findById(Long id) {
        List<String> mangaIds = collectionRepository.findAllByUserId(id);
        return mangaRepository.findAllById(mangaIds.stream().map(Long::parseLong).toList());
    }

    public void saveCollection(CollectionDTO collection) {
        collectionRepository.save(collectionDTOToCollectionMapper(collection));
    }

    private static Collection collectionDTOToCollectionMapper(CollectionDTO collection) {
        return Collection.builder()
                .idUser(collection.userId)
                .idManga(Integer.parseInt(collection.mangaId)).build();
    }
}
