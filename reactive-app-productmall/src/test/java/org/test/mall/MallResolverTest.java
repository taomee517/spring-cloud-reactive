package org.test.mall;


import org.demo.mall.service.HttpClientIPAddressResolver;
import org.demo.mall.service.impl.DefaultHttpClientIPAddressResolver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;

import static org.assertj.core.api.Assertions.assertThat;

public class MallResolverTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private HttpClientIPAddressResolver resolver;

    @Before
    public void before() {
        resolver = new DefaultHttpClientIPAddressResolver();
    }

    @Test
    public void shouldThrowIllegalArgumentsException_WhenInputNull(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("require exchange.");

        resolver.resolve(null);
    }


    @Test
    public void shouldBeNull_WhenHeaderIsEmpty(){
        MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
        String resolve = resolver.resolve(exchange);

        String expectedIp = null;
//        assert resolve == expectedIp;
        assertThat(resolve).isEqualTo(expectedIp);
    }


    @Test
    public void shouldBeNull_whenXForwardedForHeaderIsUnknown() {
        ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").header("X-Forwarded-For", "UNKNOWN"));
        String output = resolver.resolve(exchange);
        String expected = null;
        assertThat(output).isEqualTo(expected);
    }

    @Test
    public void shouldBe127_0_0_1_whenXForwardedForHeaderIs127_0_0_1() {
        ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").header("X-Forwarded-For", "127.0.0.1"));
        String output = resolver.resolve(exchange);
        String expected = "127.0.0.1";
        assertThat(output).isEqualTo(expected);
    }

    @Test
    public void shouldBe127_0_0_1_whenRemoteAddrIs127_0_0_1() {
        ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").remoteAddress(InetSocketAddress.createUnresolved("127.0.0.1", 80)));
        String output = resolver.resolve(exchange);
        String expected = "127.0.0.1:80";

        assertThat(output).isEqualTo(expected);
    }

}
