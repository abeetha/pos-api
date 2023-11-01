package com.springbootacademy.batch6POS.service.impl;

import com.springbootacademy.batch6POS.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch6POS.dto.queryInterfaces.OrderDetailInterface;
import com.springbootacademy.batch6POS.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch6POS.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.batch6POS.entity.Order;
import com.springbootacademy.batch6POS.entity.OrderDetails;
import com.springbootacademy.batch6POS.repo.CustomerRepo;
import com.springbootacademy.batch6POS.repo.ItemRepo;
import com.springbootacademy.batch6POS.repo.OrderDetailRepo;
import com.springbootacademy.batch6POS.repo.OrderRepo;
import com.springbootacademy.batch6POS.service.OrderService;
import com.springbootacademy.batch6POS.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomers()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if(orderRepo.existsById(order.getOrderId())) {
            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
                    }.getType());
            for(int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }
            if (orderDetails.size() > 0) {
                orderDetailRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return  null;
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailInterface> orderDetailsDTOS = orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));

        List<ResponseOrderDetailsDTO> list = new ArrayList<>();
        for (OrderDetailInterface o : orderDetailsDTOS) {
            ResponseOrderDetailsDTO r = new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getCustomerNumber(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                list,
                orderRepo.countAllOrderDetails(status)
        );
        return paginatedResponseOrderDetails;
    }
}
