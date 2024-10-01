package com.collector.my_collector_inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @JsonProperty("mal_id")
    public final int malId;
    @JsonProperty("url")
    public final String url;
    @JsonProperty("images")
    public final Images images;
    @JsonProperty("approved")
    public final boolean approved;
    @JsonProperty("titles")
    public final Title[] titles;
    @JsonProperty("title")
    public final String title;
    @JsonProperty("title_english")
    public final String titleEnglish;
    @JsonProperty("title_japanese")
    public final String titleJapanese;
    @JsonProperty("type")
    public final String type;
    @JsonProperty("chapters")
    public final int chapters;
    @JsonProperty("volumes")
    public final int volumes;
    @JsonProperty("status")
    public final String status;
    @JsonProperty("publishing")
    public final boolean publishing;
    @JsonProperty("published")
    public final Published published;
    @JsonProperty("favorites")
    public final int favorites;
    @JsonProperty("synopsis")
    public final String synopsis;
    @JsonProperty("background")
    public final String background;
    @JsonProperty("authors")
    public final Author[] authors;
    @JsonProperty("genres")
    public final Genre[] genres;
    @JsonProperty("explicit_genres")
    public final Genre[] explicitGenres;
    @JsonProperty("themes")
    public final Theme[] themes;
}

