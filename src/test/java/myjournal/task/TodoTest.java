package myjournal.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] read", new Todo("read").toString());
        assertEquals("[T][ ] watch movie /at 12:00", new Todo("watch movie /at 12:00").toString());
    }
}
