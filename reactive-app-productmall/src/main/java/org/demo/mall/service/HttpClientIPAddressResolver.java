package org.demo.mall.service;

import org.springframework.web.server.ServerWebExchange;

public interface HttpClientIPAddressResolver {

    /**
     * 获取调用端IP
     * @param exchange
     * @return
     */
    String resolve(ServerWebExchange exchange);
}
