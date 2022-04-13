package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    private Event event = new Event("sell book", "2020-04-21");

    @Test
    public void getTime_eventInitialized_localDateReturned() {
        assertEquals(event.getTime().toString(), "2020-04-21");
    }

    @Test
    public void getDescription_eventInitialized_descriptionReturned() {
        assertEquals(event.getDescription(), "sell book");
    }

    @Test
    public void getStatusIcon_eventInitialized_statusIconReturned() {
        assertEquals(event.getStatusIcon(), " ");
    }

    @Test
    public void toString_eventInitialized_descriptionAndTimeReturned() {
        assertEquals(event.toString(), "[E][ ] sell book (at: 21 April 2020)");
    }

    @Test
    public void markAsDone_eventInitialized_taskMarkedAsDone() {
        Event event = new Event("sell book", "2020-04-21");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] sell book (at: 21 April 2020)");
    }
}
