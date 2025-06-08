package com.epam.learn.javaadvanced.web.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RakutenApiResponse {
    @JsonProperty("Items")
    private List<ItemContainer> items;
    @JsonProperty("count")
    private Integer count;

    @Getter
    @Setter
    public static class ItemContainer {
        @JsonProperty("Item")
        private RakutenItem item;
    }
}
