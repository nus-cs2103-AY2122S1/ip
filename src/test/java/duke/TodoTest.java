package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoToString(){
        assertEquals("[T][ ] placeholder", new Todo("placeholder").toString());
    }

    @Test
    public void testTodoMarkAsDone(){
        Task temp = new Todo("placeholder");
        temp.markAsDone();
        assertEquals("[T][X] placeholder", temp.toString());
    }
}
