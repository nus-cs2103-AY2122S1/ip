package gnosis.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import gnosis.model.Todo;
import gnosis.util.GnosisException;


public class TaskControllerTest {
    @Test
    public void getTasksNullListNullPointerExceptionThrown() {
        TaskController taskCommandManager = new TaskController();
        assertNull(taskCommandManager.getTasks(), "ERROR: There is tasks");
    }

    @Test
    public void addTodoToTaskManager_someValue_success() throws GnosisException {
        TaskController taskCommandManager = new TaskController();
        Todo td = taskCommandManager.addTodo("JUNIT Test");
        assertEquals(td, taskCommandManager.getTasks().get(0));
    }
}
