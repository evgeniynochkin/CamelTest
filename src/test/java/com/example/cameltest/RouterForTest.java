package com.example.cameltest;

import com.example.cameltest.processor.JsonProcessor;
import com.example.cameltest.service.UserService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Тестовый роутер
 *
 * @author Evgeniy Nochkin
 */
@Component
public class RouterForTest extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:test")
                .end()
                .process(new JsonProcessor())
                .bean(UserService.class, "addUser(${body})");
    }
}
