import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import whobot.main.UI;
import whobot.main.WhoBotException;
import whobot.task.Deadline;
import whobot.task.Event;
import whobot.task.Task;
import whobot.task.Todo;
import whobot.utils.Storage;
import whobot.utils.TaskList;

public class TaskListTest {

    private Todo todo = new Todo("Buy A New Book");
    private Deadline deadline = new Deadline("Return Books /by 28/09/2021");
    private Event event = new Event("Team Meeting /at 27/09/2021 16:00");
    private ArrayList<Task> list = new ArrayList<>();

    public TaskListTest() throws WhoBotException {
    }

    /***
     * Tests the Addition Function
     */
    @Test
    public void testAddToList() {
        File file = new File("test1.txt");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Storage storage = new Storage("test1.txt");
            TaskList taskList = new TaskList(storage);
            UI ui = new UI();

            taskList.addTodo("todo Buy A New Book", ui);
            list.add(todo);
            assertTrue(taskList.getList().contains(todo), "addTodo() Function Error");


            taskList.addDeadline("deadline Return Books /by 28/09/2021", ui);
            list.add(deadline);
            assertTrue(taskList.getList().contains(deadline), "addDeadline() Function Error");

            taskList.addEvent("event Team Meeting /at 27/09/2021 16:00", ui);
            list.add(event);
            assertTrue(taskList.getList().contains(event), "addEvent() Function Error");

        } catch (WhoBotException e) {
            fail("Task Addition Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /***
     * Tests the ordering of Tasks
     */
    @Test
    public void testListOrder() {
        File file = new File("test2.txt");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Storage storage = new Storage("test2.txt");
            TaskList taskList = new TaskList(storage);
            taskList.getList().add(todo);
            taskList.getList().add(deadline);
            taskList.getList().add(event);
            Collections.sort(taskList.getList());

            ArrayList<Task> orderedList = new ArrayList<>();
            orderedList.add(event);
            orderedList.add(deadline);
            orderedList.add(todo);

            assertEquals(orderedList, taskList.getList(), "Task Ordering Error On Addition");
        } catch (WhoBotException e) {
            fail("Task Addition Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /***
     * Tests marking tasks as done
     */
    @Test
    public void testMarkAsDone() {
        File file = new File("test3.txt");
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Storage storage = new Storage("test3.txt");
            TaskList taskList = new TaskList(storage);
            taskList.getList().add(todo);
            taskList.getList().add(deadline);
            taskList.getList().add(event);
            event.markAsDone();
            Collections.sort(taskList.getList());

            ArrayList<Task> orderedList = new ArrayList<>();
            orderedList.add(deadline);
            orderedList.add(todo);
            orderedList.add(event);

            assertEquals(orderedList, taskList.getList(), "Task Ordering Error On Marking As Done");
        } catch (WhoBotException e) {
            fail("Task Addition Error");
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
