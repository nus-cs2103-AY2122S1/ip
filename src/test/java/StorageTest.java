import static org.junit.jupiter.api.Assertions.fail;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Task;
import whobot.task.Todo;
import whobot.utils.Storage;
import whobot.utils.TaskList;

public class StorageTest {

    private Todo todo = new Todo("Buy A New Book");
    private Deadline deadline = new Deadline("Return Books /by 28/09/2021");
    private Event event = new Event("Team Meeting /at 27/09/2021 16:00");
    private ArrayList<Task> list = new ArrayList<>();

    public StorageTest() throws WhoBotException {
    }

    /***
     * Tests the WhoBot's Memory Storage
     */
    @Test
    public void testRetention() {
        File file = new File("test.txt");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Storage storage1 = new Storage("test.txt");
            TaskList taskList1 = new TaskList(storage1);

            taskList1.getList().add(todo);
            taskList1.getList().add(deadline);
            taskList1.getList().add(event);
            Collections.sort(taskList1.getList());
            storage1.saveMemory(taskList1.getList());

            Storage storage2 = new Storage("test.txt");
            TaskList taskList2 = new TaskList(storage2);

            assertEquals(taskList2.getList(), taskList1.getList(), "Memory Storage Store and Load Error");
        } catch (WhoBotException e) {
            fail("Storage Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
