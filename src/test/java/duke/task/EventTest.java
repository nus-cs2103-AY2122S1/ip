package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void createEvent() {
        String desc = "TEST EVENT";
        Task task = Event.of(false, desc, "2020-02-02");
        assertEquals(desc, task.getDescription());
        assertEquals(" ", task.getStatusIcon());
        task = Event.of(true, desc, "2020-02-02");
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void completeEvent() {
        String desc = "TEST EVENT";
        Task task = Event.of(false, desc, "2020-02-02");
        task.markDone();
        assertEquals(true, task.isDone());
    }

    @Test
    public void makeEventStrings() {
        String desc = "TEST EVENT";
        Task task = Event.of(false, desc, "2020-02-02");

        assertEquals("[E][ ] TEST EVENT (at: Feb 2 2020)", task.toString());
        assertEquals("E|0|TEST EVENT|2020-02-02", task.toStorageString());
        task.markDone();
        assertEquals("[E][X] TEST EVENT (at: Feb 2 2020)", task.toString());
        assertEquals("E|1|TEST EVENT|2020-02-02", task.toStorageString());
    }

    @Test
    public void createEvent_invalidDate_exceptionThrown() {
        String desc = "TEST EVENT";
        assertThrows(DateTimeException.class, () -> Event.of(false, desc, "NOT VALID DATE"));
    }

}
