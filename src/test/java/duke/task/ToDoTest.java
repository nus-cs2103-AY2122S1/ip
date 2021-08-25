package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_descriptionOfToDo_toDoStringReturned() {
        assertEquals("[T][ ] study", new ToDo("study").toString());
    }
}
