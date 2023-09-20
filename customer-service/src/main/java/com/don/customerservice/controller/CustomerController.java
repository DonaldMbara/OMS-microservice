package com.don.customerservice.controller;

import com.don.customerservice.dto.CustomerRequest;
import com.don.customerservice.dto.CustomerResponse;
import com.don.customerservice.model.Customer;
import com.don.customerservice.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerDetails(@RequestBody CustomerRequest customerRequest){
        customerService.createCustomerDetails(customerRequest);
    }

    @GetMapping("/{CustomerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerDetails(@PathVariable("CustomerId") String id){
        return customerService.getCustomerDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer does not exist with id: " + id));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getCustomersDetails(){
        return customerService.getCustomersDetailsList();
    }

    @PutMapping("/{CustomerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerDetails(@PathVariable("CustomerId") String customerId, @RequestBody Customer customer) {
        customerService.updateCustomerDetails(customerId,customer);
    }

}
