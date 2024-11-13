package com.kim.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange)
                .then(Mono.from(Mono.fromRunnable(() -> {
                    Long beginVisit = exchange.getAttribute(BEGIN_VISIT_TIME);
                    if (beginVisit != null) {
                        URI uri = exchange.getRequest().getURI();
                        log.info("访问接口主机: {}", uri.getHost());
                        log.info("访问接口端口: {}", uri.getPort());
                        log.info("访问接口URL: {}", uri.getPath());
                        log.info("访问接口URL参数: {}", uri.getRawQuery());
                        log.info("访问接口时长: {}ms", System.currentTimeMillis() - beginVisit);
                        log.info("===============================================================");
                    }

                })));
    }
    /**
     * 数字越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
