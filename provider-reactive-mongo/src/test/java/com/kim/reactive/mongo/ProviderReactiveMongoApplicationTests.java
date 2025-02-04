package com.kim.reactive.mongo;

import com.kim.reactive.mongo.controller.ProductController;
import com.kim.reactive.mongo.dto.ProductDto;
import com.kim.reactive.mongo.service.ProductService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class ProviderReactiveMongoApplicationTests {
    @Resource
    private WebTestClient webTestClient;

    @MockitoBean
    private ProductService productService;

    @Test
    public void addProductTest() {
        Mono<ProductDto> mobile = Mono.just(new ProductDto("101", "mobile", 1, 1000));
        when(productService.saveProduct(mobile))
                .thenReturn(mobile);

        webTestClient.post().uri("/products")
                .body(Mono.just(mobile), ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    public void getProductsTest() {
        Flux<ProductDto> productDtoFlux = Flux.just(
                new ProductDto("102", "mobile", 1, 1000),
                new ProductDto("103", "laptop", 1, 2000)
        );
        when(productService.getProducts())
                .thenReturn(productDtoFlux);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto("102", "mobile", 1, 1000))
                .expectNext(new ProductDto("103", "laptop", 1, 2000))
                .verifyComplete();
    }

    @Test
    public void getProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 1000));
        when(productService.getProduct(any()))
                .thenReturn(productDtoMono);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products/102")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getName().equals("mobile"))
                .verifyComplete();
    }


    @Test
    public void updateProductTest() {
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 1, 1000));
        when(productService.updateProduct(productDtoMono, "102"))
                .thenReturn(productDtoMono);

        webTestClient.put().uri("/products/update/102")
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deleteProductTest() {
        given(productService.deleteProduct(any()))
                .willReturn(Mono.empty());

        webTestClient.delete().uri("/products/delete/102")
                .exchange()
                .expectStatus().isOk();
    }

}
