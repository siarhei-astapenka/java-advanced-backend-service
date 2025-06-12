package com.epam.learn.javaadvanced.web.model.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SportResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
