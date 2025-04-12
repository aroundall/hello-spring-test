package org.amuji.spring.test.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration(proxyBeanMethods = false)
public class LogHandlingRouter {


    @Bean
    public RouterFunction<ServerResponse> route(SampleLogHandler logHandler) {
        return RouterFunctions
                .route(GET("/log/hello"), logHandler::hello);
    }
}
