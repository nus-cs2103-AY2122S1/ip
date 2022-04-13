package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    @Test
    public void testToStringConversion() {
        ToDos todo1 = new ToDos("CS2103");
        assertEquals("[T][ ] CS2103", todo1.toString());

        ToDos todo2 = new ToDos("CS2103", true);
        assertEquals("[T][X] CS2103", todo2.toString());

        ToDos todo3 = new ToDos("CS2103", false);
        assertEquals("[T][ ] CS2103", todo3.toString());
    }

    @Test
    public void testSaveOutputConversion() {
        ToDos todo1 = new ToDos("CS2103");
        assertEquals("T | CS2103 | 0", todo1.saveOutput());

        ToDos todo2 = new ToDos("CS2103", true);
        assertEquals("T | CS2103 | 1", todo2.saveOutput());

        ToDos todo3 = new ToDos("CS2103", false);
        assertEquals("T | CS2103 | 0", todo3.saveOutput());
    }
}
