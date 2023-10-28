package com.don.customerservice.controller;

import com.don.customerservice.dto.CustomerRequest;
import com.don.customerservice.dto.CustomerResponse;
import com.don.customerservice.model.Customer;
import com.don.customerservice.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerDetails(@RequestBody CustomerRequest customerRequest){
        customerService.createCustomerDetails(customerRequest);
    }

    @GetMapping("/id/{CustomerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerDetails(@PathVariable("CustomerId") int customerId) {
        Optional<CustomerResponse> customerResponse = customerService.getCustomerDetails(customerId);

        return customerResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/email/{Email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> getCustomerDetailsByEmail(@PathVariable("Email") String email) {
        Optional<CustomerResponse> customerResponse = customerService.getCustomerDetailsByEmail(email);

        return customerResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getCustomersDetails(){return customerService.getCustomersDetailsList();}


    @PutMapping("/update/{Email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerDetails(@PathVariable("Email") String email, @RequestBody Customer customer) {
        customerService.updateCustomerDetails(email,customer);
    }

}
