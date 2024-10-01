package com.collector.my_collector_inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    @JsonProperty("jpg")
    public final ImageFormat jpg;
    @JsonProperty("webp")
    public final ImageFormat webp;

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageFormat {
        @JsonProperty("image_url")
        public final String imageUrl;
        @JsonProperty("small_image_url")
        public final String smallImageUrl;
        @JsonProperty("large_image_url")
        public final String largeImageUrl;
    }
}