package com.jahia.service;

import com.jahia.model.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface CustomerRepository extends ElasticsearchRepository<CustomerModel, String> {

    Page<CustomerModel> findByFName(String author, Pageable pageable);

    Page<CustomerModel> findById(String id, Pageable pageable);

}
