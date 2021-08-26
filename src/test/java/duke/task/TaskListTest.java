package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void delete() {
        TaskList taskList = new TaskList();
        Todo test1 = new Todo("test1");
        Todo test2 = new Todo("test1");
        Todo test3 = new Todo("test3");
        taskList.add(test1);
        taskList.add(test2);
        taskList.add(test3);
        taskList.delete(2);
        assertEquals(taskList.get(2), test3, "`delete()` should remove the task from the list");
    }

    @Test
    void getLength() {
        TaskList taskList = new TaskList();
        for (int i = 0; i < 10; i++) {
            taskList.add(new Todo("test"));
        }
        assertEquals(taskList.getLength(), 10, "`getLength()` should return the length of list.");
    }


    @Test
    void get() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        taskList.add(todo);
        assertEquals(taskList.get(1), todo, "`get()` should return the task inside the list.");
    }


    @Test
    void isEmpty() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty(), "Empty list should return true for `isEmpty()`.");
    }
}