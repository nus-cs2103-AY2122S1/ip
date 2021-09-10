package duke.task;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeIncompleteException;
import duke.exceptions.DukeInvalidFormatException;
import duke.exceptions.DukeWrongCommandException;

public class TestEvent {
    /**
     * Test a workable Deadline object
     */
    @Test
    public void test1() {
        Event eventTestObject1 = new Event("CS2103T lecture /at 2021-09-10");
        assertDoesNotThrow(() -> {
            Event eventTestObject2 = new Event("CS2100 lecture /at 2021-09-14");
        });
        String expected1 = "[E][ ] CS2103T lecture (at Sep 10 2021)";
        assertEquals(expected1, eventTestObject1.toString());
        eventTestObject1.markAsCompleted();
        String expected2 = "[E][X] CS2103T lecture (at Sep 10 2021)";
        assertEquals(expected2, eventTestObject1.toString());
        String expected3 = "Sep 10 2021";
        assertEquals(expected3, eventTestObject1.getDate());
        String expected4 = "[E][X] CS2103T lecture (at 2021-09-10)";
        assertEquals(expected4, eventTestObject1.save());
    }

    /**
     * Test a throwable Deadline object
     */
    @Test
    public void test2() {
        assertThrows(DukeIncompleteException.class, () -> {
            Event eventTestObject1 = new Event("");
        });
        assertThrows(DukeWrongCommandException.class, () -> {
            Event eventTestObject2 = new Event("an event");
        });
        assertThrows(DukeWrongCommandException.class, () -> {
            Event eventTestObject3 = new Event("an event /by today");
        });
        assertThrows(DukeInvalidFormatException.class, () -> {
            Event eventTestObject4 = new Event("an event /at today");
        });
    }
}
