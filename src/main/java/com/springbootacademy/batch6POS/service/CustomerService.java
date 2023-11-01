package com.springbootacademy.batch6POS.service;

import com.springbootacademy.batch6POS.dto.CustomerDTO;
import com.springbootacademy.batch6POS.dto.request.RequestUpdateCustomerDTO;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(RequestUpdateCustomerDTO customerDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int customerId);

    CustomerDTO getCustomerByNic(String nic);

    List<CustomerDTO> getAllCustomersByState(boolean status);

    List<CustomerDTO> getAllCustomersByStateAndName(String name);
}
