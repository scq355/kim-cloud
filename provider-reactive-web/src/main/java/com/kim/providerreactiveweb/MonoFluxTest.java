package com.kim.providerreactiveweb;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("javawebflux")
                .then(Mono.error(new RuntimeException("exception")))
                .log();
        monoString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux
                .just("Spring", "SpringBoot", "Hibernate", "microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("exception")))
                .concatWithValues("cloud")
                .log();
        fluxString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }
}
