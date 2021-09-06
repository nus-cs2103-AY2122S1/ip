package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testTodoToString() {
        assertEquals("[T][ ] placeholder", new Todo("placeholder").toString());
    }

    @Test
    public void testTodoMarkAsDone() {
        Task temp = new Todo("placeholder");
        temp.markAsDone();
        assertEquals("[T][X] placeholder", temp.toString());
    }
}
