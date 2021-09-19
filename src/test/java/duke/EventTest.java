package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void getDate() {
        LocalDate date = LocalDate.of(2021, 8, 22);
        Event event = new Event(" Read Book", date);
        assertEquals("2021-08-22", event.getDate().toString());
    }

    @Test
    void setDone() {
        LocalDate date = LocalDate.of(2021, 8, 22);
        Event event = new Event(" Read Book", date);
        Event eventDone = new Event(" Read Book", true, date);
        assertEquals("[E] [ ] Read Book (at: Aug 22 2021)", event.toString());
        assertEquals(eventDone.toString(), event.setDone().toString());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2021, 8, 22);
        Event event = new Event(" Read Book", date);
        Event eventDone = new Event(" Read Book", true, date);
        assertEquals("[E] [ ] Read Book (at: Aug 22 2021)", event.toString());
        assertEquals("[E] [X] Read Book (at: Aug 22 2021)", eventDone.toString());
    }
}
