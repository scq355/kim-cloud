package com.kim.providerreactiveweb.dao;

import com.kim.providerreactiveweb.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
public class CustomerDao {


    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            log.error("sleepException", e);
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Generating Customer " + i))
                .mapToObj(i -> new Customer(i, "Customer" + i)).toList();
    }


    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Generating Customer in stream " + i))
                .map(i -> new Customer(i, "Customer" + i));
    }


    public Flux<Customer> getCustomersList() {
        return Flux.range(1, 10)
                .doOnNext(i -> System.out.println("Generating Customer in stream " + i))
                .map(i -> new Customer(i, "Customer" + i));
    }


}
