package com.epam.learn.javaadvanced.domain.dto;

import com.epam.learn.javaadvanced.domain.entity.ItemEntity;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

/**
 * DTO for {@link ItemEntity}
 */
@Value
@Builder
@ToString
public class ItemEntityDto {
    Integer id;
    String name;
}