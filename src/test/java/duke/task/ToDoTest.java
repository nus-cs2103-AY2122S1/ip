package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toStringTest() {
        ToDo toDo = new ToDo("buy bread");
        assertEquals("[T][ ] buy bread", toDo.toString());
    }

    @Test
    public void saveDataTest() {
        ToDo toDo = new ToDo("buy bread");
        assertEquals("todo 0 buy bread", toDo.saveData());
    }
}
