package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDo_to_string_correct() {
        ToDo t = new ToDo("Test ToDo");
        assertEquals("[T][ ] Test ToDo", t.toString());
    }

    @Test
    public void toDo_format_save_correct() {
        ToDo t = new ToDo("Test ToDo");
        assertEquals("T%,false%,Test ToDo", t.formatSave());
    }

}
