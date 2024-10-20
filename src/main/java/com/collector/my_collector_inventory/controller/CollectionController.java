package com.collector.my_collector_inventory.controller;

import com.collector.my_collector_inventory.dto.CollectionDTO;
import com.collector.my_collector_inventory.entities.Manga;
import com.collector.my_collector_inventory.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{id}/get")
    public ResponseEntity<List<Manga>> getUserCollection(@PathVariable Long id) {
        List<Manga> allMangas = collectionService.findById(id);
        return ResponseEntity.ok(allMangas);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCollection(@RequestBody CollectionDTO collection) {
        collectionService.saveCollection(collection);
        return ResponseEntity.ok("Collection successfully added");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> removeFromCollection(@RequestBody CollectionDTO collection) {
        collectionService.deleteByIds(collection);
        return ResponseEntity.ok("Collection successfully added");
    }
}