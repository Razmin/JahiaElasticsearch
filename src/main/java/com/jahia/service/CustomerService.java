package com.jahia.service;

import com.jahia.model.CustomerModel;
import com.jahia.model.CustomerObj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface CustomerService {

    CustomerModel save(CustomerModel customer);

    void delete(CustomerModel customer);

    Iterable<CustomerModel> findAll();

    Page<CustomerModel> findByFName(String fName, PageRequest pageRequest);

    Page<CustomerModel> findById(String id, PageRequest pageRequest);

    CustomerModel updatebyId(CustomerObj customer);

    CustomerModel populateCustomer(CustomerObj customer);

}
