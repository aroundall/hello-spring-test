package org.amuji.spring.test.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Processor {
    public void process() {
        log.warn("Do something...");
    }
}
