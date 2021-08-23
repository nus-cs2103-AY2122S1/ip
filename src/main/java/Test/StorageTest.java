package Test;

import WhoBot.Main.UI;
import WhoBot.Main.WhoBotException;
import WhoBot.Task.Deadline;
import WhoBot.Task.Event;
import WhoBot.Task.Task;
import WhoBot.Task.Todo;
import WhoBot.Utils.Storage;
import WhoBot.Utils.TaskList;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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
