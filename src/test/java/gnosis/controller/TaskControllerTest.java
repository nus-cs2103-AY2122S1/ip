package gnosis.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gnosis.model.Todo;
import gnosis.util.GnosisException;


public class TaskControllerTest {

    @Test
    public void addTodoToTaskManager_someValue_success() throws GnosisException {
        TaskController taskCommandManager = new TaskController();
        Todo td = taskCommandManager.addTodo("JUNIT Test");
        int size = taskCommandManager.getTasks().size();
        assertEquals(td, taskCommandManager.getTasks().get(size - 1));
    }
}
