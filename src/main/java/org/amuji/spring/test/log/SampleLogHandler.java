package org.amuji.spring.test.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SampleLogHandler {

     public Mono<ServerResponse> hello(ServerRequest request) {
         log.info("Received the request to write a log");
         return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(new LogHandlingResult("Done")));
     }
}
