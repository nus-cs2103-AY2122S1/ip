package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testNewToDo() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
    }

}
