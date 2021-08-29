package duke;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class TaskListTest {
    private static final Path TEMP = Paths.get("C:\\Users\\ethan\\Desktop\\CS2103T\\ip\\data\\test.txt");
    private static final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage(TEMP, UI);
    private static final TaskList TASKS = new TaskList(STORAGE, UI);

    @Test
    public void testAddTasks() {
        int n = 7;
        int init = TASKS.sizeOf();
        for(int i = 0; i < n; i++) {
            TASKS.addTodo("test");
            TASKS.addDeadline(new String[] {"test", "00/00/0000 0000"});
            TASKS.addEvent(new String[] {"test", "test"});
        }
        int after = TASKS.sizeOf();

        assertEquals(init + 3 * n, after);
        STORAGE.clearFile();
    }

    @Test
    public void testDeleteTasks() {
        int n = 5;
        for(int i = 0; i < n; i++) {
            TASKS.addTodo("test");
        }
        int init = TASKS.sizeOf();
        TASKS.deleteTask(n - 1);
        int after = TASKS.sizeOf();

        assertEquals(init - 1, after);
        STORAGE.clearFile();
    }

}
