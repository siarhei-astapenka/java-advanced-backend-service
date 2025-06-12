package com.epam.learn.javaadvanced.web.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RakutenItem {
    @JsonProperty("genreId")
    private String genreId;

    @JsonProperty("itemName")
    private String itemName;
}
