import org.junit.jupiter.api.Test;
import whobot.main.UI;
import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Task;
import whobot.task.Todo;
import whobot.utils.Storage;
import whobot.utils.TaskList;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.fail;
import static org.testng.Assert.assertEquals;


public class StorageTest {

    Todo todo = new Todo("Buy A New Book");
    Deadline deadline = new Deadline("Return Books /by 28/09/2021");
    Event event = new Event("Team Meeting /at 27/09/2021 16:00");
    ArrayList<Task> list = new ArrayList<>();

    public StorageTest() throws WhoBotException {
    }

    @Test
    public void TestRetention() {
        File file = new File("test.txt");
        try {
            Storage storage1 = new Storage("test.txt");
            TaskList taskList1 = new TaskList(storage1);
            UI ui = new UI();

            taskList1.getLIST().add(todo);
            taskList1.getLIST().add(deadline);
            taskList1.getLIST().add(event);
            Collections.sort(taskList1.getLIST());
            storage1.saveMemory(taskList1.getLIST());

            Storage storage2 = new Storage("test.txt");
            TaskList taskList2 = new TaskList(storage2);

            assertEquals(taskList2.getLIST(), taskList1.getLIST(), "Memory Storage Store and Load Error");
        } catch (WhoBotException e) {
            fail("Storage Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
