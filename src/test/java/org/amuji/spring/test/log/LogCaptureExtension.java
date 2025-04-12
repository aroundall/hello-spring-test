package org.amuji.spring.test.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.junit.jupiter.api.extension.*;

import java.util.List;

public class LogCaptureExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    private TestLogAppender logAppender;
    private Configuration logConfig;

    @Override
    public void afterEach(ExtensionContext context) {
        if (logConfig != null && logAppender != null) {
            logConfig.getRootLogger().removeAppender(logAppender.getName());

            logAppender.stop();
            logAppender.clear();

            final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            ctx.updateLoggers();
        }

    }

    @Override
    public void beforeEach(ExtensionContext context) {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        logConfig = ctx.getConfiguration();

        logAppender = new TestLogAppender();
        logAppender.start(); // Ensure the appender is started

        logConfig.getRootLogger().addAppender(logAppender, null, null);

        ctx.updateLoggers();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == TestLogAppender.class;
    }

    @Override
    public TestLogAppender resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType() == TestLogAppender.class) {
            return logAppender;
        }
        return null;
    }

    public List<String> logs() {
        if (logAppender == null) {
            return List.of();
        }

        return logAppender.getEvents().stream()
                .map(e -> e.getMessage().getFormattedMessage()).toList();
    }
}
