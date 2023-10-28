package com.don.customerservice.service;

import com.don.customerservice.dto.CustomerRequest;
import com.don.customerservice.dto.CustomerResponse;
import com.don.customerservice.model.Customer;
import com.don.customerservice.repoository.CustomerRepository;
import com.don.customerservice.util.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomerDetails(CustomerRequest customerRequest){
        Customer customer = Customer.builder()
                .customer_area(customerRequest.getCustomer_area())
                .customer_street(customerRequest.getCustomer_street())
                .customer_city(customerRequest.getCustomer_city())
                .customer_postal_code(customerRequest.getCustomer_postal_code())
                .customer_dob(customerRequest.getCustomer_dob())
                .customer_cell_phone(customerRequest.getCustomer_cell_phone())
                .customer_email(customerRequest.getCustomer_email())
                .wallet_balance(customerRequest.getWallet_balance())
                .customer_firt_name(customerRequest.getCustomer_first_name())
                .customer_last_name(customerRequest.getCustomer_last_name())
                .build();

        customerRepository.save(customer);
        log.info("Customer {} is saved", customer.getId());
    }

    public Optional<CustomerResponse> getCustomerDetails(int id){
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer does not exist with id: " + id)));

        return customer.map(CustomerMapper::mapToCustomerResponse);
    }

    public Optional<CustomerResponse> getCustomerDetailsByEmail(String email){
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer does not exist with email: " + email)));

        return customer.map(CustomerMapper::mapToCustomerResponse);
    }


    public List<CustomerResponse> getCustomersDetailsList(){
        List<Customer> customers = customerRepository.findAll();
        
        return  customers.stream()
                .map(CustomerMapper::mapToCustomerResponse)
                .toList();
    }

    public void updateCustomerDetails(String email, Customer customer){
        Customer updateCustomer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer does not exist with email: "+ customer.getCustomer_email()));


        updateCustomer.setCustomer_area(customer.getCustomer_area());
        updateCustomer.setCustomer_street(customer.getCustomer_street());
        updateCustomer.setCustomer_city(customer.getCustomer_city());
        updateCustomer.setCustomer_postal_code(customer.getCustomer_postal_code());
        updateCustomer.setCustomer_dob(customer.getCustomer_dob());
        updateCustomer.setCustomer_cell_phone(customer.getCustomer_cell_phone());
        updateCustomer.setCustomer_email(customer.getCustomer_email());
        updateCustomer.setWallet_balance(customer.getWallet_balance());
        updateCustomer.setCustomer_firt_name(customer.getCustomer_firt_name());
        updateCustomer.setCustomer_last_name(customer.getCustomer_last_name());

        customerRepository.save(updateCustomer);
        log.info("Customer {} is saved", customer.getId());
    }
}
