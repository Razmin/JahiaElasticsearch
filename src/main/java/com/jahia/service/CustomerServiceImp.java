package com.jahia.service;

import com.jahia.model.CustomerModel;
import com.jahia.model.CustomerObj;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private Client client;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerModel save(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(CustomerModel customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Iterable<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<CustomerModel> findByFName(String fname, PageRequest pageRequest) {
        return customerRepository.findByFName(fname, pageRequest);
    }

    @Override
    public Page<CustomerModel> findById(String id, PageRequest pageRequest) {
        return customerRepository.findById(id, pageRequest);
    }

    @Override
    public CustomerModel updatebyId(CustomerObj customer) {

        Page<CustomerModel> customers = findById(customer.getId(), new PageRequest(0,10));
        if (customers.getTotalElements()>0) {
            for (CustomerModel c:customers) {
                c.setfName(customer.getfName());
                c.setlName(customer.getlName());
                return save(c);
            }
        } else {
            return null;
        }
        return null;
    }

    public CustomerModel populateCustomer(CustomerObj customer) {
        CustomerModel cModel = new CustomerModel();
        cModel.setfName(customer.getfName());
        cModel.setlName(customer.getlName());
        cModel.setId(customer.getId());
        return cModel;
    }

}
