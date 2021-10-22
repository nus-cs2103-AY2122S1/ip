package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


class TaskListTest {

    @Test
    void get_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task0 = new Deadline("return book", "2/2/2020");
        Task task1 = new Event("read book", "2/2/2020 1800");
        Task task2 = new Todo("homework");
        tasks.add(task0);
        tasks.add(task1);
        tasks.add(task2);
        TaskList taskList = new TaskList(tasks);
        assertEquals(task1, taskList.get(1));
    }

    @Test
    void get_indexBeyondScope_errorThrow() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task task0 = new Deadline("return book", "2/2/2020");
            Task task1 = new Event("read book", "2/2/2020 1800");
            Task task2 = new Todo("homework");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            TaskList taskList = new TaskList(tasks);

            taskList.get(4);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("OOPS!!! The index is beyond the scope!"));
        }
    }

    @Test
    void length_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task0 = new Deadline("return book", "2/2/2020");
        Task task1 = new Event("read book", "2/2/2020 1800");
        Task task2 = new Todo("homework");
        tasks.add(task0);
        tasks.add(task1);
        tasks.add(task2);
        TaskList taskList = new TaskList(tasks);
        assertEquals(3, taskList.length());
    }

    @Test
    void add_success() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task task0 = new Deadline("return book", "2/2/2020");
            Task task1 = new Event("read book", "2/2/2020 1800");
            Task task2 = new Todo("homework");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            TaskList taskList = new TaskList(tasks);
            taskList.add(new Todo("new todo"));
        } catch (Exception e) {
            fail("There is an error on adding function");
        }
    }

    @Test
    void done_success() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task task0 = new Deadline("return book", "2/2/2020");
            Task task1 = new Event("read book", "2/2/2020 1800");
            Task task2 = new Todo("homework");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            TaskList taskList = new TaskList(tasks);
            taskList.done(1);
        } catch (Exception e) {
            fail("There is an error on done() function");
        }
    }

    @Test
    void delete_success() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Task task0 = new Deadline("return book", "2/2/2020");
            Task task1 = new Event("read book", "2/2/2020 1800");
            Task task2 = new Todo("homework");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            TaskList taskList = new TaskList(tasks);
            taskList.delete(1);
        } catch (Exception e) {
            fail("There is an error on deleting function");
        }
    }
}
