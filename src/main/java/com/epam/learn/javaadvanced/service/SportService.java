package com.epam.learn.javaadvanced.service;

import com.epam.learn.javaadvanced.domain.entity.ItemEntity;
import com.epam.learn.javaadvanced.domain.repository.ItemRepository;
import com.epam.learn.javaadvanced.service.mapper.ItemMapper;
import com.epam.learn.javaadvanced.web.model.internal.SportResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SportService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public SportService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public Mono<SportResponse> createSport(String name) {
        return itemRepository.existsByName(name)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new DuplicateKeyException("Sport with name '" + name + "' already exists"));
                    }
                    return itemRepository.save(ItemEntity.builder().name(name).build()).map(itemMapper::toSportResponse);
                });
    }

    public Flux<SportResponse> searchSports(String query) {
        return itemRepository.findByNameContainingIgnoreCase(query).map(itemMapper::toSportResponse);
    }
}
