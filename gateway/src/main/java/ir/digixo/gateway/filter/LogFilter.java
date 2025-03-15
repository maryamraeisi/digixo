package ir.digixo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@Order(1)
public class LogFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        long start = System.currentTimeMillis();
        //pre process

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            //post processing
            long end = System.currentTimeMillis();
            log.info("request time is: {}",end-start);
        }));
    }
}
