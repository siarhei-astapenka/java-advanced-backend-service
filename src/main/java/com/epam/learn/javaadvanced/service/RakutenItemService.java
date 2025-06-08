package com.epam.learn.javaadvanced.service;

import com.epam.learn.javaadvanced.domain.dto.ItemEntityDto;
import com.epam.learn.javaadvanced.domain.repository.ItemRepository;
import com.epam.learn.javaadvanced.service.mapper.ItemMapper;
import com.epam.learn.javaadvanced.web.dto.external.RakutenApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RakutenItemService {

    private final WebClient webClient;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Value("${rakuten.api.applicationId}")
    private String applicationId;

    public RakutenItemService(WebClient webClient, ItemRepository itemRepository, ItemMapper itemMapper) {
        this.webClient = webClient;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public Flux<ItemEntityDto> fetchAndSaveItems(String keyword) {
        return fetchItemsFromRakuten(keyword)
                .flatMapMany(response -> Flux.fromIterable(response.getItems()))
                .map(itemMapper::toItemEntityDto)
                .flatMap(dto -> itemRepository.upsertWithReturn(itemMapper.toItemEntity(dto)))
                .map(itemMapper::toItemEntityDto);
    }

    private Mono<RakutenApiResponse> fetchItemsFromRakuten(String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/IchibaItem/Search/20220601")
                        .queryParam("format", "json")
                        .queryParam("keyword", keyword)
                        .queryParam("applicationId", applicationId)
                        .build())
                .retrieve()
                .bodyToMono(RakutenApiResponse.class);
    }
}
