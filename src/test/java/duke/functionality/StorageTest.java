package duke.functionality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.TodoStub;

public class StorageTest {

    @Test
    public void addTaskTest() {
        String filePath = System.getProperty("user.dir");
        Storage storage = new Storage(filePath);
        Task todo = new TodoStub("test");
        storage.addTask(todo);
        assertEquals(todo, storage.getTask(0));
    }

    @Test
    public void deleteTask() {
        String filePath = System.getProperty("user.dir");
        Storage storage = new Storage(filePath);
        Task todo = new TodoStub("test");
        storage.addTask(todo);
        assertEquals(todo, storage.deleteTask(0));
    }
}
