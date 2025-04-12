package org.amuji.spring.test.log;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SampleLogHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SampleLogHandler.class);

    public Mono<ServerResponse> hello(ServerRequest request) {
        log.info("Received the request to write a log");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new LogHandlingResult("Done")));
    }
}
