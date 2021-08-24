package dino;

import dino.task.TaskList;
import dino.exception.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTest {
    @Test
    public void throwEmptyListException_success() {
        String errorMessage = null;
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.printTaskList();
        } catch (EmptyListException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("😐 You don't have any task in your list~", errorMessage);
    }

}
