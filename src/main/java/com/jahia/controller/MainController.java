package com.jahia.controller;

import com.jahia.model.CustomerModel;
import com.jahia.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Iterable<CustomerModel> customers = customerService.findAll();
        ArrayList<CustomerModel> customerList = new ArrayList<>();
        for (CustomerModel customer:customers) {
            customerList.add(customer);
        }
        mav.addObject("customers", customerList);

        mav.setViewName("index");
        return mav;
    }

}
