package com.collector.my_collector_inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @JsonProperty("mal_id")
    public final int malId;
    @JsonProperty("type")
    public final String type;
    @JsonProperty("name")
    public final String name;
    @JsonProperty("url")
    public final String url;
}