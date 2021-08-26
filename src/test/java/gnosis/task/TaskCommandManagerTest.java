package gnosis.task;

import gnosis.util.GnosisException;
import gnosis.model.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskCommandManagerTest {

    @Test
    public void getTasks_nullList_NullPointerExceptionThrown() {
        TaskCommandManager taskCommandManager = new TaskCommandManager(null);
        assertNull(taskCommandManager.getTasks(), "ERROR: There is tasks");
    }

    @Test
    public void addTodoToTaskManager_someValue_success() throws GnosisException {
        TaskCommandManager taskCommandManager = new TaskCommandManager(new ArrayList<>());
        Todo td = taskCommandManager.addTodo("JUNIT Test");
        assertEquals(td,taskCommandManager.getTasks().get(0));
    }




}
