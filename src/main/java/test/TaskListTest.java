package test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void get() {
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
    void length() {
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
    void add() {
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
    void done() {
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
    void delete() {
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