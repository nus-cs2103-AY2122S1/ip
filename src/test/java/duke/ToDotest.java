package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDotest {

    @Test
    public void markAsDone_void_success() {
        ToDo testToDo = new ToDo("description");
        testToDo.markAsDone();
        assertEquals("[T][X] description", testToDo.getTaskString());
    }

    @Test
    public void getTaskString_void_success() {
        assertEquals("[T][ ] description", new ToDo("description").getTaskString());
    }
}