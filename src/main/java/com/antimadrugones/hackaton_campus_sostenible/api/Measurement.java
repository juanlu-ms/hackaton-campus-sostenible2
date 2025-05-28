package com.antimadrugones.hackaton_campus_sostenible.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("levelPercent")
    private Integer levelPercent;
}