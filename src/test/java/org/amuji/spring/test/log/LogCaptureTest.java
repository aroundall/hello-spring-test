package org.amuji.spring.test.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class LogCaptureTest {
    private TestLogAppender logAppender;
    private Configuration logConfig;

    @BeforeEach
    void setUp() {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        logConfig = ctx.getConfiguration();

        logAppender = new TestLogAppender();
        logAppender.start(); // Ensure the appender is started

        logConfig.getRootLogger().addAppender(logAppender, null, null);

        ctx.updateLoggers();
    }

    @AfterEach
    void tearDown() {
        if (logConfig != null && logAppender != null) {
            logConfig.getRootLogger().removeAppender(logAppender.getName());

            logAppender.stop();
            logAppender.clear();

            final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            ctx.updateLoggers();
        }
    }

    public List<String> logs() {
        if (logAppender == null) {
            return List.of();
        }

        return logAppender.getEvents().stream()
                .map(e -> e.getMessage().getFormattedMessage()).toList();
    }
}
