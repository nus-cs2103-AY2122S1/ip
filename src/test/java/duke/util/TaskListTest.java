package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;

class TaskListTest {

    @Test
    void getTaskList() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        assertEquals(taskArrayList, taskList.getTaskList());
        Todo todo = new Todo("read book");
        taskArrayList.add(todo);
        taskList.addTask(todo);
        assertEquals(taskArrayList, taskList.getTaskList());
    }

    @Test
    void getSize() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(0, taskList.getSize());
        taskList.addTask(new Todo("read book"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void getTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo todo = new Todo("read book");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void addTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo todo = new Todo("read book");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    void deleteTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo todo = new Todo("read book");
        taskList.addTask(todo);
        assertEquals(1, taskList.getSize());
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }
}
