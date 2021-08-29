package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final Event eventIncomplete = new Event("test 1", false,
            LocalDateTime.parse("31/12/2021 2359", dtf));
    private final Event eventComplete = new Event("test 2", true,
            LocalDateTime.parse("01/08/2021 0800", dtf));
    private final Event eventDifferentDateTimeFormat = new Event("test 3", false,
            LocalDateTime.of(2021, 9, 1, 12, 0));
    @Test
    public void toString_incompleteEvent_success() {
        assertEquals("[E][] test 1 (at: 31 Dec 2021 11:59 PM)", eventIncomplete.toString());
    }

    @Test
    public void toString_completeEvent_success() {
        assertEquals("[E][X] test 2 (at: 01 Aug 2021 08:00 AM)", eventComplete.toString());
    }

    @Test
    public void toString_differentDateFormat_success() {
        assertEquals("[E][] test 3 (at: 01 Sep 2021 12:00 PM)", eventDifferentDateTimeFormat.toString());
    }
}
