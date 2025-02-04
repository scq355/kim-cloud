package com.kim.providerreactiveweb.router;

import com.kim.providerreactiveweb.dto.Customer;
import com.kim.providerreactiveweb.handler.CustomerHandler;
import com.kim.providerreactiveweb.handler.CustomerStreamHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/router/customers",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method =  RequestMethod.GET,
                    beanClass = CustomerHandler.class,
                    beanMethod = "loadCustomers",
                    operation = @Operation(
                            operationId = "loadCustomers",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Customer.class
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/router/customers/{input}",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method =  RequestMethod.GET,
                    beanClass = CustomerHandler.class,
                    beanMethod = "findCustomer",
                    operation = @Operation(
                            operationId = "findCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Customer.class
                                                    )
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "customer not found with given id"
                                    )
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "input")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/router/customers/save",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method =  RequestMethod.POST,
                    beanClass = CustomerHandler.class,
                    beanMethod = "saveCustomer",
                    operation = @Operation(
                            operationId = "saveCustomer",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(
                                                    schema = @Schema(
                                                            implementation = Customer.class
                                                    )
                                            )
                                    )
                            },
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    implementation = Customer.class
                                            )
                                    )
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("router/customers", customerHandler::loadCustomers)
                .GET("router/customers/stream", customerStreamHandler::getCustomers)
                .GET("router/customers/{input}", customerHandler::findCustomer)
                .POST("router/customers/save", customerHandler::saveCustomer)
                .build();
    }
}
