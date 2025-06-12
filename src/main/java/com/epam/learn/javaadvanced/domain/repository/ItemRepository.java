package com.epam.learn.javaadvanced.domain.repository;

import com.epam.learn.javaadvanced.domain.entity.ItemEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveCrudRepository<ItemEntity, Integer> {
    Mono<ItemEntity> findByName(String name);
    Flux<ItemEntity> findByNameContainingIgnoreCase(String name);
    Mono<Boolean> existsByName(String name);
}
