package com.collector.my_collector_inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ListMangaDTO {
    @JsonProperty("data")
    private final List<MangaData> data;
    @JsonProperty("pagination")
    private final Pagination pagination;

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
        @JsonProperty("last_visible_page")
        public final int lastVisiblePage;
        @JsonProperty("has_next_page")
        public final boolean hasNextPage;
        @JsonProperty("items")
        public final Items items;
    }

    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Items {
        @JsonProperty("count")
        public final int count;
        @JsonProperty("total")
        public final int total;
        @JsonProperty("per_page")
        public final int perPage;
    }
}