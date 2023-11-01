package com.springbootacademy.batch6POS.util.mappers;

import com.springbootacademy.batch6POS.dto.ItemDTO;
import com.springbootacademy.batch6POS.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemDTO> entityListDtoList(List<Item> items);

    List<ItemDTO> pageToList(Page<Item>items);
}
