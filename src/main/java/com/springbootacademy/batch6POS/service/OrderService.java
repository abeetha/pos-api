package com.springbootacademy.batch6POS.service;

import com.springbootacademy.batch6POS.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch6POS.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
