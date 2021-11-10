package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void constructorWithEmptyDescriptionTest() {
        try {
            new ToDo("");
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }

        try {
            new ToDo("", true);
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void taskToLineTest() {
        assertEquals("T | 0 | project", new ToDo("project").taskToLine());
        assertEquals("T | 1 | project", new ToDo("project", true).taskToLine());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] project", new ToDo("project").toString());
        assertEquals("[T][X] project", new ToDo("project", true).toString());
    }
}
