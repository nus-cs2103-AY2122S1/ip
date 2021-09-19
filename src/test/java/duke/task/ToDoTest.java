package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ToDoTest {
    @Test
    public void toStringTest() {
        String input = "mumu";
        String expected = "[T][ ] mumu";
        try {
            ToDo toDo = new ToDo(input);
            assertEquals(expected, toDo.toString());
        } catch (DukeException e) {
            //This shouldn't happen
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getSaveTest() {
        String input = "mumu";
        String expected = "T1|mumu";
        try {
            ToDo toDo = new ToDo(input);
            toDo.setDone();
            assertEquals(expected, toDo.getSave());
        } catch (DukeException e) {
            //This shouldn't happen
            System.out.println(e.getMessage());
        }
    }
}
