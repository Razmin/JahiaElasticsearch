package com.jahia.controller;

import com.jahia.model.CustomerModel;
import com.jahia.model.CustomerObj;
import com.jahia.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/addingCustomer.do", method = RequestMethod.POST)
    public ModelAndView addingCustomer(HttpServletRequest request) {
        String page = "addCustomer";
        ModelAndView mav = new ModelAndView();
        mav.setViewName(page);
        return mav;
    }

    @RequestMapping(value = "/deleteCustomer.do", method = RequestMethod.POST)
    public ModelAndView deleteCustomer(HttpServletRequest request, @RequestParam("id") String customerId, RedirectAttributes rm ) {
        String page = "index.do";
        ModelAndView mav = new ModelAndView();
        Page<CustomerModel> customers = customerService.findById( customerId, new PageRequest(0, 1) );
        if (customers.getTotalElements()>0) {
            for (CustomerModel customer : customers) {
                customerService.delete(customer);
                rm.addFlashAttribute("message", "The customer has been deleted");
                break;
            }
        } else {
            rm.addFlashAttribute("message", "Customer not found!");
        }
        mav.setViewName("redirect:/"+page);
        return mav;
    }

    @RequestMapping(value = "/editCustomer.do", method = RequestMethod.POST)
    public ModelAndView addingCustomer(HttpServletRequest request, @RequestParam("id") String customerId, RedirectAttributes rm ) {
        String page = "addCustomer";
        ModelAndView mav = new ModelAndView();
        Page<CustomerModel> customers = customerService.findById( customerId, new PageRequest(0, 1) );
        if (customers.getTotalElements()>0) {
            for (CustomerModel customer : customers) {
                CustomerObj customerObj = new CustomerObj(customerId, customer);
                mav.addObject("customer", customerObj);
                break;
            }
        } else {
            mav.setViewName("redirect:/"+page);
            rm.addFlashAttribute("message", "Customer not found!");
        }
        mav.setViewName(page);
        return mav;
    }

    @RequestMapping(value = "/addCustomer.do", method = RequestMethod.POST)
    public ModelAndView addingCustomer(HttpServletRequest request,
                                       @ModelAttribute CustomerObj customer,
                                       RedirectAttributes rm ) {
        String page = "index.do";
        ModelAndView mav = new ModelAndView();
        if (StringUtils.isEmpty(customer.getId())) {
            CustomerModel cModel = customerService.populateCustomer(customer);
            cModel.setId(UUID.randomUUID().toString());
            if (customerService.save(cModel) != null) {
                rm.addFlashAttribute("message", "Successfully added.");
            } else {
                rm.addFlashAttribute("message", "Error in adding new customer");
            }
        } else {
            if (customerService.updatebyId(customer)!=null) {
                rm.addFlashAttribute("message", "Successfully updated.");
            } else {
                rm.addFlashAttribute("message", "Error in editing the customer.");
            }
        }
        mav.setViewName("redirect:/"+page);
        return mav;
    }

}
