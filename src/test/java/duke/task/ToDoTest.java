package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        String input = "mumu";
        String expected = "[T][ ] mumu";
        ToDo toDo = new ToDo(input);
        assertEquals(expected, toDo.toString());
    }

    @Test
    public void getSaveTest() {
        String input = "mumu";
        String expected = "T1|mumu";
        ToDo toDo = new ToDo(input);
        toDo.setDone();
        assertEquals(expected, toDo.getSave());
    }
}
