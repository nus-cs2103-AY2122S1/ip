package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void testDelete() {
        TaskList taskList = new TaskList();
        Todo test1 = new Todo("test1");
        Todo test2 = new Todo("test1");
        Todo test3 = new Todo("test3");
        taskList.add(test1);
        taskList.add(test2);
        taskList.add(test3);
        taskList.delete(2);
        assertEquals(test3, taskList.get(2), "`delete()` should remove the task from the list");
    }

    @Test
    void testGetLength() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            taskList.add(new Todo("test"));
        }
        assertEquals(10, taskList.getLength(), "`getLength()` should return the length of list.");
    }


    @Test
    void testGet() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        taskList.add(todo);
        assertEquals(todo, taskList.get(1), "`get()` should return the task inside the list.");
    }


    @Test
    void testIsEmpty() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty(), "Empty list should return true for `isEmpty()`.");
    }
}