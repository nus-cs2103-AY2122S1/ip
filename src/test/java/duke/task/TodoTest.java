package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] This is a test", new Todo("This is a test").toString());
        assertEquals("[T][X] This is a test", new Todo("This is a test", true).toString());
    }

    @Test
    public void testStorageFormatConversion() {
        assertEquals("T%This is a test%false", new Todo("This is a test").toStorageFormat());
        assertEquals("T%This is a test%true",
            new Todo("This is a test", true).toStorageFormat());
    }

    @Test
    public void testMarkDone() {
        Task todo = new Todo("This is a test");
        assertEquals("[T][ ] This is a test", todo.toString());
        todo.markDone();
        assertEquals("[T][X] This is a test", todo.toString());
    }
}
