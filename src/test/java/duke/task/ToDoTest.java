package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDoInput() {
        Task test = new ToDo("hubbub");
        assertEquals(test.toString(), "[T][ ] hubbub");
    }
}
