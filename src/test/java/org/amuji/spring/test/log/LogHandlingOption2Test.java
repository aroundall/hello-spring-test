package org.amuji.spring.test.log;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ExtendWith(LogCaptureExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LogHandlingOption2Test {
    @Autowired
    private WebTestClient client;

    @Test
    public void the_log_should_be_captured(TestLogAppender logAppender) {
        client.get().uri("/log/hello")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(LogHandlingResult.class).value(result -> {
                    assertThat(result.getResult()).isEqualTo("Done");
                    assertThat(logAppender.getEvents().stream().map(logEvent -> logEvent.getMessage().getFormattedMessage()).toList())
                            .contains("Received the request to write a log");
                });
    }

}