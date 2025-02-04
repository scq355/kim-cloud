package com.kim.providerreactiveweb.router;

import com.kim.providerreactiveweb.handler.CustomerHandler;
import com.kim.providerreactiveweb.handler.CustomerStreamHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Resource
    private CustomerHandler customerHandler;
    @Resource
    private CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("router/customers", customerHandler::loadCustomers)
                .GET("router/customers/stream", customerStreamHandler::getCustomers)
                .GET("router/customers/{input}", customerHandler::findCustomer)
                .POST("router/customers/save", customerHandler::saveCustomer)
                .build();
    }
}
