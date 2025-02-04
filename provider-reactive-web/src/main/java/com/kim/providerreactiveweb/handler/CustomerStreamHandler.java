package com.kim.providerreactiveweb.handler;

import com.kim.providerreactiveweb.dao.CustomerDao;
import com.kim.providerreactiveweb.dto.Customer;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Resource
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customersStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
}
