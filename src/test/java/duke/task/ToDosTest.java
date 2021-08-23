package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {

    @Test
    public void toStringTest() {
        ToDos toDos = new ToDos("buy bread");
        assertEquals("[T][ ] buy bread", toDos.toString());
    }

    @Test
    public void saveDataTest() {
        ToDos toDos = new ToDos("buy bread");
        assertEquals("todo 0 buy bread", toDos.saveData());
    }
}
