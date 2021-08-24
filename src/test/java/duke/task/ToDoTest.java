package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void getStatusIcon_doneToDo_returnsX() {
        ToDo doneToDo = new ToDo("").markAsDone();
        assertEquals(doneToDo.getStatusIcon(), ToDo.DONE_STATUS_INDICATOR);
    }
}
