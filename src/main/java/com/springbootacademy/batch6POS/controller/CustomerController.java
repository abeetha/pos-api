package com.springbootacademy.batch6POS.controller;

import com.springbootacademy.batch6POS.dto.CustomerDTO;
import com.springbootacademy.batch6POS.dto.request.RequestUpdateCustomerDTO;
import com.springbootacademy.batch6POS.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path="/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "abeetha";
    }

    @PutMapping(path="/update")
    public String updateCustomer(@RequestBody RequestUpdateCustomerDTO customerDTO){
        String updated = customerService.updateCustomer(customerDTO);
        return updated;
    }

    @GetMapping(
        path = "/get-by-id",
        params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
       CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    @GetMapping(path="get-all-customers")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @DeleteMapping(path = "/delete-customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int customerId){
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;
    }

    @GetMapping(
            path = "/get-by-nic",
            params = "nic"
    )
    public CustomerDTO getCustomerByNic(@RequestParam(value = "nic") String nic){
        CustomerDTO customerDTO = customerService.getCustomerByNic(nic);
        return customerDTO;
    }

    @GetMapping(path="get-all-customers-by-active-state/{status}")
    public List<CustomerDTO> getAllCustomersByState(@PathVariable(value = "status") boolean status){
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByState(status);
        return allCustomers;
    }

    @GetMapping(path="get-all-customers-by-active-state-and-name/{name}")
    public List<CustomerDTO> getAllCustomersByState(@PathVariable(value = "name") String name){
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByStateAndName(name);
        return allCustomers;
    }
}
