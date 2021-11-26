package org.demo.mall.service.impl;

import org.demo.mall.service.HttpClientIPAddressResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Objects;

public class DefaultHttpClientIPAddressResolver implements HttpClientIPAddressResolver {
    private static String CACHE_KEY = "RESOLVE_EXCHANGE_IP_ADDR";

    private static final String[] IP_ADDRESS_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    @Override
    public String resolve(ServerWebExchange exchange) {
        Assert.notNull(exchange, "require exchange.");

        Object attribute = exchange.getAttribute(CACHE_KEY);
        if(Objects.nonNull(attribute) && has((String) attribute)){
            return (String) attribute;
        }

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        for (String headerKey : IP_ADDRESS_HEADERS){
            String tempIp = headers.getFirst(headerKey);
            if(has(tempIp)){
                return cached(tempIp, exchange);
            }
        }

        String remoteAddress = getRemoteAddress(request);
        return cached(remoteAddress, exchange);
    }




    private static boolean has(String ip) {
        if (!StringUtils.hasText(ip)) {
            return false;
        }
        return !"UNKNOWN".equalsIgnoreCase(ip);
    }

    private String getRemoteAddress(ServerHttpRequest httpReq) {
        InetSocketAddress removeAddress = httpReq.getRemoteAddress();
        if (removeAddress == null) {
            return null;
        }
        return removeAddress.toString();
    }

    private String cached(String ip, ServerWebExchange exchange) {
        Map<String, Object> attributes = exchange.getAttributes();
        if (StringUtils.hasText(ip)) {
            attributes.put(CACHE_KEY, ip);
        } else {
            attributes.remove(CACHE_KEY, ip);
        }
        return ip;
    }
}
