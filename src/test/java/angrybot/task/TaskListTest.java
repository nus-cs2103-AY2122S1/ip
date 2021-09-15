package angrybot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import angrybot.exception.NoSuchTaskException;
import org.junit.jupiter.api.Test;



public class TaskListTest {

    @Test
    public void addTest() {
        TaskList list = new TaskList();
        list.add(new ToDo("sleep", false));
        assertEquals(1, list.size());
    }

    @Test
    public void loadFromListTest() {
        TaskList tasklist = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("sleep", false));
        tasklist.loadFromList(list);
        assertEquals(1, tasklist.size());
    }

    @Test
    public void deleteTest() {
        TaskList tasklist = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        list.add(new ToDo("sleep", false));
        tasklist.loadFromList(list);
        try {
            tasklist.delete(1);
            assertEquals(0, tasklist.size());
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
    }
}
