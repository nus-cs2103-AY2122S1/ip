import whobot.main.UI;
import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Task;
import whobot.task.Todo;
import whobot.utils.Storage;
import whobot.utils.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    Todo todo = new Todo("Buy A New Book");
    Deadline deadline = new Deadline("Return Books /by 28/09/2021");
    Event event = new Event("Team Meeting /at 27/09/2021 16:00");
    ArrayList<Task> list = new ArrayList<>();

    public TaskListTest() throws WhoBotException {
    }

    @Test
    public void TestAddToList() {
        File file = new File("test.txt");
        try {
            Storage storage = new Storage("test.txt");
            TaskList taskList = new TaskList(storage);
            UI ui = new UI();

            taskList.addTODO("todo Buy A New Book", ui);
            list.add(todo);
            assertTrue(taskList.getLIST().contains(todo), "addTodo() Function Error");


            taskList.addDeadline("deadline Return Books /by 28/09/2021", ui);
            list.add(deadline);
            assertTrue(taskList.getLIST().contains(deadline), "addDeadline() Function Error");

            taskList.addEvent("event Team Meeting /at 27/09/2021 16:00", ui);
            list.add(event);
            assertTrue(taskList.getLIST().contains(event), "addEvent() Function Error");

        } catch (WhoBotException e) {
            fail("Task Addition Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void TestListOrder() {
        File file = new File("test.txt");
        try {
            Storage storage = new Storage("test.txt");
            TaskList taskList = new TaskList(storage);
            taskList.getLIST().add(todo);
            taskList.getLIST().add(deadline);
            taskList.getLIST().add(event);
            Collections.sort(taskList.getLIST());

            ArrayList<Task> orderedList = new ArrayList<>();
            orderedList.add(event);
            orderedList.add(deadline);
            orderedList.add(todo);

            assertEquals(orderedList, taskList.getLIST(), "Task Ordering Error On Addition");
        } catch (WhoBotException e) {
            fail("Task Addition Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    public void TestMarkAsDone() {
        File file = new File("test.txt");
        try {
            Storage storage = new Storage("test.txt");
            TaskList taskList = new TaskList(storage);
            UI ui = new UI();
            taskList.getLIST().add(todo);
            taskList.getLIST().add(deadline);
            taskList.getLIST().add(event);
            event.markAsDone();
            Collections.sort(taskList.getLIST());

            ArrayList<Task> orderedList = new ArrayList<>();
            orderedList.add(deadline);
            orderedList.add(todo);
            orderedList.add(event);

            assertEquals(orderedList, taskList.getLIST(), "Task Ordering Error On Marking As Done");
        } catch (WhoBotException e) {
            fail("Task Addition Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
