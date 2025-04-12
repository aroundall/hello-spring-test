package org.amuji.spring.test.log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestLogAppender extends AbstractAppender {
    private final List<LogEvent> events = Collections.synchronizedList(new ArrayList<>());

    public TestLogAppender() {
        // Using a simple default layout, can be customized
        super("TestLogAppender", null, PatternLayout.createDefaultLayout(), true, Property.EMPTY_ARRAY);
    }

    @Override
    public void append(LogEvent event) {
        events.add(event.toImmutable());
    }

    public List<LogEvent> getEvents() {
        return events;
    }

    public void clear() {
        events.clear();
    }
}
