package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    ToDo t = new ToDo("JUnit test iP", true);

    @Test
    public void testFormatForSave() {
        assertEquals("T | 1 | JUnit test iP", t.toSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[T][X] JUnit test iP", t.toString());
    }
}
