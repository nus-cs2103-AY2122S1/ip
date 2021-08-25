package myjournal.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] read", new Todo("read").toString());
        assertEquals("[T][ ] watch movie /at 12:00", new Todo("watch movie /at 12:00").toString());
    }
}
