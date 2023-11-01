package com.springbootacademy.batch6POS.controller;

import com.springbootacademy.batch6POS.dto.ItemDTO;
import com.springbootacademy.batch6POS.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch6POS.dto.request.RequestItemSaveDTO;
import com.springbootacademy.batch6POS.service.ItemService;
import com.springbootacademy.batch6POS.util.StandardResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path ="/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestItemSaveDTO requestItemSaveDTO){
        String add = itemService.addItem(requestItemSaveDTO);
        /*return "saved";*/
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success","Saved"),
                HttpStatus.OK
        );
    }
    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )
    public List<ItemDTO> getItemByNameAndActiveState(@RequestParam(value = "name")String itemName){
         List<ItemDTO> itemDTOS = itemService.getItemByNameAndActiveState(itemName);
         return itemDTOS;
    }

    @GetMapping(
            path = "/get-by-name-1",
            params = "name"
    )
    public ResponseEntity<StandardResponse> getItemByNameAndActiveState1(@RequestParam(value = "name")String itemName) {
        List<ItemDTO> itemDTOS = itemService.getItemByNameAndActiveState(itemName);
/*        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",itemDTOS), HttpStatus.OK
        );
        return response;*/
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",itemDTOS),
                HttpStatus.OK
        );
    }

     @GetMapping(
            path = {"/get-all-item-by-status"},
            params = {"page","size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllItemsActive(
          @RequestParam(value = "page") int page,
          @RequestParam(value = "size") @Max(50) int size,
          @RequestParam(value = "activeState") boolean activeState
    ){
     PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsActive(page,size,activeState);
     return new ResponseEntity<StandardResponse>(
             new StandardResponse(200,"Success",paginatedResponseItemDTO),
             HttpStatus.OK
     );
  }
}
