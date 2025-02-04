package com.kim.providerreactiveweb.controller;


import com.kim.providerreactiveweb.dto.Customer;
import com.kim.providerreactiveweb.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RequestMapping("customers")
@RestController
public class CustomController {
    @Resource
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomers() {
        return customerService.loadAllCustomers();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersStream() {
        return customerService.loadAllCustomersStream();
    }
}
