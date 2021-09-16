import org.junit.jupiter.api.Test;
import duke.task.Event;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createNewEventTest(){
        Event event = new Event("Project meeting", LocalDate.parse("2021-01-01"));
        assertEquals("[E][ ] Project meeting (at: Jan 1 2021)", event.toString());
    }

    @Test
    public void markEventAsDoneTest(){
        Event event = new Event("Project meeting", LocalDate.parse("2021-01-01"));
        event.completeTask();
        assertEquals("[E][x] Project meeting (at: Jan 1 2021)", event.toString());
    }
}
