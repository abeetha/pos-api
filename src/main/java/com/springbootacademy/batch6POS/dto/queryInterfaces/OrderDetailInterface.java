package com.springbootacademy.batch6POS.dto.queryInterfaces;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {
    String getCustomerName();

    String getCustomerAddress();

    ArrayList getCustomerNumber();

    Date getDate();

    Double getTotal();
}
