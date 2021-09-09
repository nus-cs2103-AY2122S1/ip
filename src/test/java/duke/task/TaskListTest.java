package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.DukeDuplicateTaskException;

public class TaskListTest {

    @Test
    void testDelete() {
        TaskList taskList = new TaskList();
        Todo test1 = new Todo("test1");
        Todo test2 = new Todo("test2");
        Todo test3 = new Todo("test3");
        try {
            taskList.add(test1);
            taskList.add(test2);
            taskList.add(test3);
        } catch (DukeDuplicateTaskException ignored) {
            // no handling
        }
        taskList.delete(2);
        assertEquals(test3, taskList.get(2), "`delete()` should remove the task from the list");
    }

    @Test
    void testGetLength() {
        TaskList taskList = new TaskList();
        try {
            for (int i = 0; i < 10; i++) {
                taskList.add(new Todo("test" + i));
            }
        } catch (DukeDuplicateTaskException ignored) {
            // no handling
        }
        assertEquals(10, taskList.getLength(), "`getLength()` should return the length of list.");
    }


    @Test
    void testGet() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        try {
            taskList.add(todo);
        } catch (DukeDuplicateTaskException ignored) {
            // no handling
        }
        assertEquals(todo, taskList.get(1), "`get()` should return the task inside the list.");
    }


    @Test
    void testIsEmpty() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty(), "Empty list should return true for `isEmpty()`.");
    }
}
