package com.collector.my_collector_inventory.repositories;

import com.collector.my_collector_inventory.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    @Query(value = "SELECT id_manga FROM collection WHERE id_user = :id", nativeQuery = true)
    List<String> findAllByUserId(@Param("id") Long id);
}