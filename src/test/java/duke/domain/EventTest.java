package duke.domain;

import duke.shared.InvalidDateException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    Event event;
    static final String EVENT_NAME = "Some Event";
    static final String DATE_RANGE_STRING_SHORT = "3/5/2052 1700-1800";
    static final String INVALID_DATE_RANGE_STRING_SHORT = "3/5/2052 1700-1530";
    static final String DATE_RANGE_STRING_LONG = "3/5/2052 1700-3/5/2052 1800";
    static final String INVALID_DATE_RANGE_STRING_LONG = "3/5/2052 1700-3/5/2052 1530";
    static final String DAY = "3/5/2052";
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        event = null;
    }

    @Test
    void createEvent_validDateRange_success() {
        new Event(EVENT_NAME + "1", DATE_RANGE_STRING_SHORT);
        new Event(EVENT_NAME + "2", DATE_RANGE_STRING_LONG);
    }

    @Test
    void createEvent_invalidDateRange_failure() {
        assertThrows(InvalidDateException.class, () ->
                new Event(EVENT_NAME + "1", INVALID_DATE_RANGE_STRING_SHORT));
        assertThrows(InvalidDateException.class, ()->
                new Event(EVENT_NAME + "2", INVALID_DATE_RANGE_STRING_LONG));
    }

    @Test
    void isOccurringOnDay() {
        assertTrue(new Event(EVENT_NAME + "1", DATE_RANGE_STRING_SHORT).isOccurringOnDay(DAY));
        assertTrue(new Event(EVENT_NAME + "2", DATE_RANGE_STRING_LONG).isOccurringOnDay(DAY));
    }
}
