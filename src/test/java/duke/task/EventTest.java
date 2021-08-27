package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventInstanceCreation_createGeneralInstance_success() {
        String expected = "[E][ ] test sentence(23 AUGUST 2021 7.30 PM - 9.00 PM)";
        Event event = new Event("test sentence", "2021-08-23 1930-2100", false);
        assertEquals(expected, event.toString());
    }

    @Test
    public void eventInstanceCreationDate_createGeneralInstance_success() {
        String expected = "[E][ ] test sentence(23 AUGUST 2021)";
        Event event = new Event("test sentence", "2021-08-23", false);
        assertEquals(expected, event.toString());
    }

    @Test
    public void eventInstanceCreationOthers_createGeneralInstance_success() {
        String expected = "[E][ ] test sentence(by sunday or something)";
        Event event = new Event("test sentence", "by sunday or something", false);
        assertEquals(expected, event.toString());
    }
}
