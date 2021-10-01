package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void serialize_sequence() {

        String serial = "Task:event\n"
                + "\tName:NAME\n"
                + "\tDone:true\n"
                + "\tAt:AT\n";
        Task event = new Event("NAME", true, "AT");

        assertEquals(
                serial.toString(),
                event.serialize().toString()
        );
    }

    @Test
    void testToString_done() {
        String toString = "[E][X] NAME(at: AT)";
        Task event = new Event("NAME", true, "AT");
        assertEquals(
                toString,
                event.toString()
        );
    }

    @Test
    void testToString_notDone() {
        String toString = "[E][ ] NAME(at: AT)";
        Task event = new Event("NAME", false, "AT");
        assertEquals(
                toString,
                event.toString()
        );
    }
}
