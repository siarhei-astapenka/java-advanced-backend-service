package com.epam.learn.javaadvanced.domain.repository;

import com.epam.learn.javaadvanced.domain.entity.ItemEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveCrudRepository<ItemEntity, Integer> {
    @Query("""
    MERGE INTO ITEMS KEY (id)
    VALUES (:#{#entity.id}, :#{#entity.name})
    """)
    Mono<ItemEntity> upsert(@Param("entity") ItemEntity entity);

    default Mono<ItemEntity> upsertWithReturn(ItemEntity entity) {
        return upsert(entity)
                .then(findById(entity.getId()));
    }
}
