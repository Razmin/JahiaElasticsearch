package com.jahia;

import com.jahia.model.CustomerModel;
import com.jahia.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(CustomerModel.class);
        esTemplate.createIndex(CustomerModel.class);
        esTemplate.putMapping(CustomerModel.class);
        esTemplate.refresh(CustomerModel.class);
    }

    @Test
    public void testSave() {

        CustomerModel customer = new CustomerModel(UUID.randomUUID().toString(), "Tom", "Fox");
        CustomerModel testCustomer = customerService.save(customer);

        assertNotNull(testCustomer.getId());
        assertEquals(testCustomer.getfName(), customer.getfName());
        assertEquals(testCustomer.getlName(), customer.getlName());

    }


    @Test
    public void testFindById() {

        String id = UUID.randomUUID().toString();
        CustomerModel customer = new CustomerModel(id, "TestFirstName", "TestLastName");
        customerService.save(customer);

        Page<CustomerModel> testCustomers = customerService.findById(id, new PageRequest(0,1));
        for (CustomerModel c:testCustomers) {
            assertNotNull(c.getId());
            assertEquals(c.getfName(), customer.getfName());
            assertEquals(c.getlName(), customer.getlName());
        }

    }

    @Test
    public void testFindByFirstName() {

        List<CustomerModel> bookList = new ArrayList<>();

        bookList.add(new CustomerModel( UUID.randomUUID().toString(), "Test First Name 1", "Test Last Name 1"));
        bookList.add(new CustomerModel( UUID.randomUUID().toString(), "Test First Name 2", "Test Last Name 2"));
        bookList.add(new CustomerModel( UUID.randomUUID().toString(), "Test First Name 3", "Test Last Name 3"));
        bookList.add(new CustomerModel( UUID.randomUUID().toString(), "Test First Name 4", "Test Last Name 4"));
        bookList.add(new CustomerModel( UUID.randomUUID().toString(), "Test First Name 5", "Test Last Name 5"));
        bookList.add(new CustomerModel( UUID.randomUUID().toString(), "Razmin", "Soltani"));

        for (CustomerModel c : bookList) {
            customerService.save(c);
        }

        Page<CustomerModel> testCustomers = customerService.findByFName("Test First Name", new PageRequest(0, 10));
        assertTrue(testCustomers.getTotalElements() > 5);

        Page<CustomerModel> byAuthor2 = customerService.findByFName("Razmin", new PageRequest(0, 10));
        assertTrue (testCustomers.getTotalElements() > 1);

    }

    @Test
    public void testDelete() {

        String id = UUID.randomUUID().toString();
        CustomerModel customerModel = new CustomerModel( id, "DeleteTest Fisrname", "DeleteTest Lastname");
        customerService.save(customerModel);
        customerService.delete(customerModel);
        Page<CustomerModel> testCustomerModels = customerService.findById(id, new PageRequest(0,1));
        assertTrue (testCustomerModels.getTotalElements() == 0);

    }

}
