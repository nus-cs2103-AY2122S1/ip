package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void constructorWithEmptyDescriptionTest() {
        try {
            new Task("");
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }

        try {
            new Task("", true);
            fail();
        } catch (Exception e) {
            assertEquals("Your description cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void taskToLineTest() {
        assertEquals(" | 0 | project", new Task("project").taskToLine());
        assertEquals(" | 1 | project", new Task("project", true).taskToLine());
    }

    @Test
    public void markDoneIconTest() {
        assertEquals("[ ]", new Task("project").markDoneIcon());
        assertEquals("[X]", new Task("project", true).markDoneIcon());
    }

    @Test
    public void toStringTest() {
        assertEquals("[ ] project", new Task("project").toString());
        assertEquals("[X] project", new Task("project", true).toString());
    }
}
