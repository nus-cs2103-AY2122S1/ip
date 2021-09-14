package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import duke.exceptions.DukeIncompleteException;
import duke.exceptions.DukeInvalidFormatException;
import duke.exceptions.DukeWrongCommandException;

public class TestDeadline {

    /**
     * Test a workable Deadline object
     */
    @Test
    public void test1() {
        Deadline deadlineTestObject1 = new Deadline("Complete CS2103T IP /by 2021-09-17");
        assertDoesNotThrow(() -> {
            Deadline deadlineTestObject2 = new Deadline("Complete CS2100 assignment /by 2021-09-15");
        });
        String expected1 = "[D][ ] Complete CS2103T IP (by Sep 17 2021)";
        assertEquals(expected1, deadlineTestObject1.toString());
        deadlineTestObject1.markAsCompleted();
        String expected2 = "[D][X] Complete CS2103T IP (by Sep 17 2021)";
        assertEquals(expected2, deadlineTestObject1.toString());
        String expected3 = "Sep 17 2021";
        assertEquals(expected3, deadlineTestObject1.getDate());
        String expected4 = "[D][X] Complete CS2103T IP (by 2021-09-17)";
        assertEquals(expected4, deadlineTestObject1.save());
    }

    /**
     * Test a throwable Deadline object
     */
    @Test
    public void test2() {
        assertThrows(DukeIncompleteException.class, () -> {
            Deadline deadlineTestObject2 = new Deadline("");
        });
        assertThrows(DukeWrongCommandException.class, () -> {
            Deadline deadlineTestObject3 = new Deadline("a deadline");
        });
        assertThrows(DukeWrongCommandException.class, () -> {
            Deadline deadlineTestObject4 = new Deadline("a deadline /at today");
        });
        assertThrows(DukeInvalidFormatException.class, () -> {
            Deadline deadlineTestObject5 = new Deadline("a deadline /by today");
        });
    }
}
