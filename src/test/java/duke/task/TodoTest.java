package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void testFormatToSave() {
        Todo td = new Todo("life :^(");
        assertEquals("T | 0 | life :^(", td.formatToSave());
    }

    @Test
    void testToString() {
        Todo td = new Todo("life :^(");
        assertEquals("[T][] life :^(", td.toString());
    }

    @Test
    void testMarkAsDoneToString() {
        Todo td = new Todo("sleep");
        td.markAsDone();
        assertEquals("[T][X] sleep", td.toString());
    }

    @Test
    void testMarkAsDoneFormatToSave() {
        Todo td = new Todo("sleep");
        td.markAsDone();
        assertEquals("T | 1 | sleep", td.formatToSave());
    }
}
