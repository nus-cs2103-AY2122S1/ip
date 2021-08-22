package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
