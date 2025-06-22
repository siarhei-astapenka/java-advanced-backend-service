package com.epam.learn.javaadvanced.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoResponse {

    @JsonProperty("Application")
    private String appName;

    @JsonProperty("Active users")
    private Integer activeUsers;

    @JsonProperty("Server load")
    private Integer serverLoad;

    @JsonProperty("Requests processed")
    private Integer requestsProcessed;

    @JsonProperty("Memory usage")
    private Integer memoryUsage;

    @JsonProperty("CPU usage")
    private Integer cpuUsage;
}
