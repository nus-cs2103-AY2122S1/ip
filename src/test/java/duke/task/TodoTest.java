package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void testFormatToSave() {
        Todo td = new Todo("cs2100 assignment");
        assertEquals("T | 0 | cs2100 assignment", td.formatToSave());
    }

    @Test
    void testToString() {
        Todo td = new Todo("ip task");
        assertEquals("[T][ ] ip task", td.toString());
    }

    @Test
    void testMarkAsDoneToString() {
        Todo td = new Todo("sleep");
        td.markAsDone();
        assertEquals("[T][✓] sleep", td.toString());
    }

    @Test
    void testMarkAsDoneFormatToSave() {
        Todo td = new Todo("sleep");
        td.markAsDone();
        assertEquals("T | 1 | sleep", td.formatToSave());
    }
}
