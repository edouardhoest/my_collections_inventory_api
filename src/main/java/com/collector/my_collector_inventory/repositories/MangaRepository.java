package com.collector.my_collector_inventory.repositories;

import com.collector.my_collector_inventory.entities.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    @Query(value = "SELECT * FROM manga LIMIT 10", nativeQuery = true)
    List<Manga> findTopTen();
}
