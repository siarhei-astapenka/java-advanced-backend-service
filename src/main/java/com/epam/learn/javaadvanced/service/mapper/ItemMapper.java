package com.epam.learn.javaadvanced.service.mapper;

import com.epam.learn.javaadvanced.domain.dto.ItemEntityDto;
import com.epam.learn.javaadvanced.domain.entity.ItemEntity;
import com.epam.learn.javaadvanced.web.dto.external.RakutenApiResponse;
import com.epam.learn.javaadvanced.web.dto.external.RakutenItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {
    // RakutenAPIResponse -> List<ItemEntityDto>
    public List<ItemEntityDto> toItemEntityDtoList(RakutenApiResponse response) {
        return response.getItems().stream()
                .map(this::toItemEntityDto)
                .collect(Collectors.toList());
    }

    // RakutenItem -> ItemEntityDto
    public ItemEntityDto toItemEntityDto(RakutenApiResponse.ItemContainer itemContainer) {
        RakutenItem rakutenItem = itemContainer.getItem();
        return ItemEntityDto.builder()
                .name(rakutenItem.getItemName())
                .build();
    }

    // ItemEntityDto -> ItemEntity
    public ItemEntity toItemEntity(ItemEntityDto dto) {
        return ItemEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public ItemEntityDto toItemEntityDto(ItemEntity dto) {
        return ItemEntityDto.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    // List<ItemEntityDto> -> List<ItemEntity>
    public List<ItemEntity> toItemEntityList(List<ItemEntityDto> dtos) {
        return dtos.stream()
                .map(this::toItemEntity)
                .collect(Collectors.toList());
    }
}
