package com.springbootacademy.batch6POS.dto.request;

import com.springbootacademy.batch6POS.entity.Customer;
import com.springbootacademy.batch6POS.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private int customers;
    private Date date;
    private Double total;
    private boolean activeState;
    private List<RequestOrderDetailsSave> orderDetails;
}
