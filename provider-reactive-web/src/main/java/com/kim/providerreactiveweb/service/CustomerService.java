package com.kim.providerreactiveweb.service;

import com.kim.providerreactiveweb.dao.CustomerDao;
import com.kim.providerreactiveweb.dto.Customer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> list = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        log.info("loadAllCustomers cost {} ms", (end - start));
        return list;
    }


    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> list = customerDao.getCustomersStream();
        long end = System.currentTimeMillis();
        log.info("loadAllCustomers cost in stream {} ms", (end - start));
        return list;
    }

}
