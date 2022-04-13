package kayu.service;

import static kayu.service.TaskList.ERROR_EMPTY_LIST;
import static kayu.service.TaskList.ERROR_INVALID_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.task.Task;
import kayu.task.Todo;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() throws StorageException {
        taskList = new TaskList();
        List<Task> tasks = IntStream.rangeClosed(1, 10)
                .boxed()
                .map(num -> new Todo("mock " + num))
                .collect(Collectors.toList());
        taskList.initializeTasks(tasks);
    }

    @Test
    public void testGetCapacity() {
        assertEquals(10, taskList.getCurrentCapacity());
    }

    @Test
    public void testAddTask() {
        int prevSize = taskList.getCurrentCapacity();
        Task newTask = new Todo("new todo here");
        taskList.addTask(newTask);
        assertEquals(prevSize + 1, taskList.getCurrentCapacity());
    }

    @Test
    public void testDeleteValidTask() {
        int taskNumber = 5;
        try {
            String expectedDesc = "mock " + taskNumber;
            Task deletedTask = taskList.deleteTask(taskNumber);
            assertEquals(expectedDesc, deletedTask.getDescription());

        } catch (KayuException exception) {
            System.out.println(exception.getMessage());
            fail();
        }
    }

    @Test
    public void testMarkTaskAsDone() {
        int taskNumber = 8;
        try {
            Task updatedTask = taskList.updateTaskAsDone(taskNumber);
            assertTrue(updatedTask.isDone());

        } catch (KayuException exception) {
            System.out.println(exception.getMessage());
            fail();
        }
    }

    @Test
    public void deleteTask_taskNumberIsInvalid_exceptionThrown() {
        int taskNumber = 12;
        try {
            taskList.deleteTask(taskNumber);
            fail();

        } catch (KayuException exception) {
            String expected = String.format(ERROR_INVALID_TASK, taskNumber);
            assertEquals(expected, exception.getMessage());
        }
    }

    @Test
    public void deleteTask_taskListIsEmpty_exceptionThrown() throws StorageException {
        int taskNumber = 12;
        taskList.initializeTasks(new ArrayList<>());
        try {
            taskList.deleteTask(taskNumber);
            fail();

        } catch (KayuException exception) {
            assertEquals(ERROR_EMPTY_LIST, exception.getMessage());
        }
    }
}
