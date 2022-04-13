package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createNewEventTest() {
        Event event = new Event("Project meeting", LocalDate.parse("2021-01-01"));
        assertEquals("[E][ ] Project meeting (at: Jan 1 2021)", event.toString());
    }

    @Test
    public void markEventAsDoneTest() {
        Event event = new Event("Project meeting", LocalDate.parse("2021-01-01"));
        event.completeTask();
        assertEquals("[E][x] Project meeting (at: Jan 1 2021)", event.toString());
    }
}
