package com.collector.my_collector_inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Published {
    @JsonProperty("from")
    public final String from;
    @JsonProperty("to")
    public final String to;
    @JsonProperty("prop")
    public final Prop prop;
    @JsonProperty("string")
    public final String string;

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Prop {
        @JsonProperty("from")
        public final DateProp from;
        @JsonProperty("to")
        public final DateProp to;
    }

    @RequiredArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor(force = true)
    public static class DateProp {
        @JsonProperty("day")
        public final int day;
        @JsonProperty("month")
        public final int month;
        @JsonProperty("year")
        public final int year;
    }

}
