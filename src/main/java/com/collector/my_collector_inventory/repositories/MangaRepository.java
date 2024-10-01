package com.collector.my_collector_inventory.repositories;

import com.collector.my_collector_inventory.entities.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends JpaRepository<MangaEntity, Long> {
    MangaEntity findMangaEntitiesByTitle(String title);
}
