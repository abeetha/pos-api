package com.springbootacademy.batch6POS.service.impl;

import com.springbootacademy.batch6POS.dto.ItemDTO;
import com.springbootacademy.batch6POS.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch6POS.dto.request.RequestItemSaveDTO;
import com.springbootacademy.batch6POS.entity.Customer;
import com.springbootacademy.batch6POS.entity.Item;
import com.springbootacademy.batch6POS.exception.NotFoundException;
import com.springbootacademy.batch6POS.repo.ItemRepo;
import com.springbootacademy.batch6POS.service.ItemService;
import com.springbootacademy.batch6POS.util.mappers.ItemMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String addItem(RequestItemSaveDTO requestItemSaveDTO) {
         Item item = modelMapper.map(requestItemSaveDTO,Item.class);
         if(!itemRepo.existsById(item.getItemId())){
             itemRepo.save(item);
             return "saved successfull";
         }else{
             throw new DuplicateKeyException("Already Added");
         }

    }

    @Override
    public List<ItemDTO> getItemByNameAndActiveState(String itemName){
        boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, b);
        /* List<ItemDTO> itemDTOS = modelMapper.map(items, new TypeToken<List<ItemDTO>>(){}.getType());*/
        if (items.size() > 0) {
            List<ItemDTO> itemDTOS = itemMapper.entityListDtoList(items);
            return itemDTOS;
        } else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsActive(int page, int size, boolean activeState) {
        Page<Item>items = itemRepo.findAllByActiveStateEquals(activeState, PageRequest.of(page,size));
        if(items.getSize()<1){
            throw new NotFoundException("No Data According to your request");
        }
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(items),
                itemRepo.countAllByActiveStateEquals(activeState)
        );
    }
}
