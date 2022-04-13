package src.main.java.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void markAsDone() {
        Event event = new Event("return book", false, "2pm");
        event.markAsDone();
        assertEquals("E | 1 | return book | 2pm", event.toString());
    }
}
