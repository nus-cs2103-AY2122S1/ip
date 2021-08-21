package lifeline.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testNewEventIsDoneIsFalse() {
        Event event = new Event("wedding", LocalDate.now(), LocalTime.of(10, 30),
                LocalTime.of(12, 30));
        assertFalse(event.isDone());
    }

    @Test
    public void testEventGetName() {
        Event event = new Event("wedding", LocalDate.now(), LocalTime.of(10, 30),
                LocalTime.of(12, 30));
        assertEquals("wedding", event.getName());
    }

    @Test
    public void testEventToString() {
        Event event = new Event("wedding", LocalDate.of(2021, 3, 4), LocalTime.of(10, 30),
                LocalTime.of(12, 30));
        assertEquals("[E][ ] wedding (at: Mar 4 2021 10:30 AM - 12:30 PM)", event.toString());
    }

    @Test
    public void testEventToggleIsDone() {
        Event event = new Event("wedding", LocalDate.now(), LocalTime.of(10, 30),
                LocalTime.of(12, 30));
        assertFalse(event.isDone());
        event.setDone(true);
        assertTrue(event.isDone());
    }
}
