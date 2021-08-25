package duke.tasks;

import duke.exceptions.AuguryException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskFactoryTest {

    @Test
    public void createTaskFromFileTest() {
        TaskFactory tf = new TaskFactory();
        Task t = tf.createTask("[T] wash dishes");
        assertFalse(t instanceof EventTask);
        assertTrue(t instanceof TodoTask);
    }

    @Test
    public void returnNullWithMalformattedEventTypeTest() {
        TaskFactory tf = new TaskFactory();
        assertNull(tf.createTask("woowo"));
    }

    @Test
    public void throwsErrorWithMalformattedTime() throws AuguryException {
        // gleaned from https://www.baeldung.com/junit-assert-exception
        TaskFactory tf = new TaskFactory();
        Exception e = assertThrows(DateTimeParseException.class, () -> {
            tf.createTask("[E] do this (at: jjjjj)");
        });

        String expectedMessage = "Text 'jjjjj' could not be parsed at index 0";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
