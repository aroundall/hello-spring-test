package org.amuji.spring.test.log;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LogHandlingTest {
    @Autowired
    private WebTestClient client;

    @Test
    public void the_log_should_be_captured() {
        client.get().uri("/log/hello")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(LogHandlingResult.class).value(result -> {
                    Assertions.assertThat(result.getResult()).isEqualTo("Done");
                });
    }

}