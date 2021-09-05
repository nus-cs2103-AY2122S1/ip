package gnosis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gnosis.model.Todo;
import gnosis.util.GnosisException;


public class TaskCommandManagerTest {

    @Test
    public void getTasksNullListNullPointerExceptionThrown() {
        TaskCommandManager taskCommandManager = new TaskCommandManager(null);
        assertNull(taskCommandManager.getTasks(), "ERROR: There is tasks");
    }

    @Test
    public void addTodoToTaskManager_someValue_success() throws GnosisException {
        TaskCommandManager taskCommandManager = new TaskCommandManager(new ArrayList<>());
        Todo td = taskCommandManager.addTodo("JUNIT Test");
        assertEquals(td, taskCommandManager.getTasks().get(0));
    }




}
