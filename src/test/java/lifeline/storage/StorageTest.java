package lifeline.storage;

import static lifeline.util.ErrorString.ERROR_UNABLE_TO_FIND_SAVED_TASKS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import lifeline.exception.LifelineException;
import lifeline.task.Task;
import lifeline.task.TaskList;
import lifeline.task.ToDo;

public class StorageTest {

    @Test
    public void load_missingSavedTasks_exceptionThrown() {
        Storage storage = new Storage("test.json");
        try {
            storage.load();
            fail();
        } catch (LifelineException e) {
            assertEquals(ERROR_UNABLE_TO_FIND_SAVED_TASKS, e.getMessage());
        }
    }

    @Test
    public void load_presentSavedTasks_success() throws LifelineException {
        Storage storage = new Storage("test.json");
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo todo = new ToDo("read book");
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        storage.save(taskList);
        TaskList loadedTaskList = storage.load();
        assertEquals(taskList.getTask(0).toString(), loadedTaskList.getTask(0).toString());
    }

    @AfterEach
    public void deleteTestFiles() {
        File testFile = new File("test.json");
        testFile.delete();
    }
}
