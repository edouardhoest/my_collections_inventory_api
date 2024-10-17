package com.collector.my_collector_inventory.repositories;

import com.collector.my_collector_inventory.entities.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    Manga findByTitle(String title);
}
