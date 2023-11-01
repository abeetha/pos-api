package com.springbootacademy.batch6POS.dto.request;

import com.springbootacademy.batch6POS.entity.Item;
import com.springbootacademy.batch6POS.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {

    private String itemName;
    private double qty;
    private Double amount;
    private int items;
}
