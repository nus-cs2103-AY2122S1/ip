package duke.functionality;

import duke.tasks.Task;
import duke.tasks.TodoStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void addTaskTest() {
        String FILE_PATH = System.getProperty("user.dir");
        Storage storage = new Storage(FILE_PATH);
        Task todo = new TodoStub("test");
        storage.addTask(todo);
        assertEquals(todo, storage.getTask(0));
    }

    @Test
    public void deleteTask() {
        String FILE_PATH = System.getProperty("user.dir");
        Storage storage = new Storage(FILE_PATH);
        Task todo = new TodoStub("test");
        storage.addTask(todo);
        assertEquals(todo, storage.deleteTask(0));
    }
}
