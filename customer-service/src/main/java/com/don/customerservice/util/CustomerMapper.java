package com.don.customerservice.util;

import com.don.customerservice.dto.CustomerResponse;
import com.don.customerservice.model.Customer;

public class CustomerMapper {
        public static CustomerResponse mapToCustomerResponse(Customer customer){
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .customer_area(customer.getCustomer_area())
                    .customer_street(customer.getCustomer_street())
                    .customer_city(customer.getCustomer_city())
                    .customer_postal_code(customer.getCustomer_postal_code())
                    .customer_dob(customer.getCustomer_dob())
                    .customer_cell_phone(customer.getCustomer_cell_phone())
                    .customer_email(customer.getCustomer_email())
                    .wallet_balance(customer.getWallet_balance())
                    .customer_first_name(customer.getCustomer_firt_name())
                    .customer_last_name(customer.getCustomer_last_name())
                    .build();
    }
}
