package com.collector.my_collector_inventory.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manga")
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String authors;

    @Column(length = 10000)
    private String description;
    private String imageUrl;
    private String categories;
    private String status;
}
