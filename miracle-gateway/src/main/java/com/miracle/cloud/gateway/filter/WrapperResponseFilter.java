package com.miracle.cloud.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.miracle.cloud.gateway.utils.Response;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class WrapperResponseFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse response = exchange.getResponse();
        DataBufferFactory bufferFactory = response.bufferFactory();

        ServerHttpResponse httpResponse = new ServerHttpResponseDecorator(response) {

            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> flux = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(flux.map(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);

                        DataBufferUtils.release(dataBuffer);
                        String content = new String(bytes, Charset.forName("utf-8"));
                        Response finalResponse = new Response();
                        finalResponse.setCode(0);
                        finalResponse.setData(content);
                        finalResponse.setMessage("success");
                        byte[] bytes1 = JSON.toJSONString(finalResponse).getBytes(StandardCharsets.UTF_8);
                        response.getHeaders().setContentLength(bytes1.length);
                        return bufferFactory.wrap(bytes1);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(httpResponse).build());
    }

    @Override
    public int getOrder() {
        return -2;
    }
}
