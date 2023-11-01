package com.springbootacademy.batch6POS.service;

import com.springbootacademy.batch6POS.dto.ItemDTO;
import com.springbootacademy.batch6POS.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch6POS.dto.request.RequestItemSaveDTO;
import javassist.NotFoundException;

import java.util.List;

public interface ItemService {

    String addItem(RequestItemSaveDTO requestItemSaveDTO);

    List<ItemDTO> getItemByNameAndActiveState(String itemName);

    PaginatedResponseItemDTO getAllItemsActive(int page, int size, boolean activeState);
}
