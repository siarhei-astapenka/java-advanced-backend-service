package com.epam.learn.javaadvanced.domain.repository;

import com.epam.learn.javaadvanced.domain.entity.ItemEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<ItemEntity, Integer> {
}
