package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    Event event = new Event("sell book", "2020-04-21");

    @Test
    public void getTimeTest() {
        assertEquals(event.getTime().toString(), "2020-04-21");
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(event.getDescription(), "sell book");
    }

    @Test
    public void getStatusIconTest() {
        assertEquals(event.getStatusIcon(), " ");
    }

    @Test
    public void toStringTest() {
        assertEquals(event.toString(), "[E][ ] sell book (at: 21 April 2020)");
    }

    @Test
    public void markAsDoneTest() {
        Event event = new Event("sell book", "2020-04-21");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] sell book (at: 21 April 2020)");
    }
}
