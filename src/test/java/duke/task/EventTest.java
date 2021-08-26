package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testFormatToSave() {
        Event e = new Event("hi yay", "26 December 2021");
        assertEquals("E | 0 | hi yay | 26 December 2021", e.formatToSave());
    }

    @Test
    void testToStringDate() {
        Event e = new Event("hi yay", "26 December 2021");
        assertEquals("[E][] hi yay (at: 26 December 2021)", e.toString());
    }

    @Test
    void testToStringNormal() {
        Event e = new Event("sleep", "12am midnight");
        assertEquals("[E][] sleep (at: 12am midnight)", e.toString());
    }

    @Test
     void testMarkAsDoneToString() {
        Event e = new Event("sleep", "12am midnight");
        e.markAsDone();
        assertEquals("[E][X] sleep (at: 12am midnight)",e.toString());
    }

    @Test
    void testMarkAsDoneFormatToSave() {
        Event e = new Event("sleep", "12am midnight");
        e.markAsDone();
        assertEquals("E | 1 | sleep | 12am midnight", e.formatToSave());
    }
}