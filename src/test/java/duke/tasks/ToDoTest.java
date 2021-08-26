package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    ToDo t = new ToDo("JUnit test iP", true);

    @Test
    public void testContainsKeyword() {
        assertTrue(t.containsKeyword("test"));
        assertFalse(t.containsKeyword("tP"));
    }

    @Test
    public void testFormatForSave() {
        assertEquals("T | 1 | JUnit test iP", t.toSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[T][X] JUnit test iP", t.toString());
    }
}
