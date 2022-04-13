package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucInvalidFormatException;
import duke.exceptions.DucWrongCommandException;

public class TestDeadline {

    /**
     * Tests a valid Deadline object
     */
    @Test
    public void testValidDeadline() {
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
     * Tests an invalid Deadline Object
     */
    @Test
    public void testInvalidDeadline() {
        assertThrows(DucIncompleteException.class, () -> {
            Deadline deadlineTestObject2 = new Deadline("");
        });
        assertThrows(DucWrongCommandException.class, () -> {
            Deadline deadlineTestObject3 = new Deadline("a deadline");
        });
        assertThrows(DucWrongCommandException.class, () -> {
            Deadline deadlineTestObject4 = new Deadline("a deadline /at today");
        });
        assertThrows(DucInvalidFormatException.class, () -> {
            Deadline deadlineTestObject5 = new Deadline("a deadline /by today");
        });
    }
}
