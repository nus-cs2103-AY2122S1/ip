package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testToString() {
        ToDo task = new ToDo("homework");
        assertEquals("[T][ ] homework", task.toString());
    }

    @Test
    void format1() {
        ToDo task = new ToDo("homework");
        assertEquals("T | 0 | homework", task.format());
    }

    @Test
    void format2() {
        ToDo task = new ToDo("homework");
        task.markDone();
        assertEquals("T | 1 | homework", task.format());
    }

    @Test
    void testMarkDone() {
        ToDo task = new ToDo("homework");
        task.markDone();
        assertEquals("[T][X] homework", task.toString());
    }

    @Test
    void testGetStatusIcon() {
        ToDo task = new ToDo("homework");
        task.markDone();
        assertEquals("X", task.getStatusIcon());
    }
}
