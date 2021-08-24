package kayu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.task.Task;
import kayu.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() throws StorageException {
        taskList = new TaskList();
        List<Task> tasks = IntStream.rangeClosed(1, 10)
                .boxed()
                .map(num -> new Todo("mock " + num))
                .collect(Collectors.toList());
        taskList.init(tasks);
    }

    @Test
    public void testGetCapacity() {
        assertEquals(10, taskList.getCapacity());
    }

    @Test
    public void testAddTask() {
        try {
            int prevSize = taskList.getCapacity();
            Task newTask = new Todo("new todo here");
            taskList.addTask(newTask);
            assertEquals(prevSize + 1, taskList.getCapacity());

        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
            fail();
        }
    }
    
    @Test
    public void testDeleteValidTask() {
        int taskNumber = 5;
        try {
            String expectedDesc = "mock " + taskNumber;
            Task deletedTask = taskList.deleteTask(taskNumber);
            assertEquals(expectedDesc, deletedTask.getDescription());
            
        } catch (DukeException exception) {
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
            
        } catch (DukeException exception) {
            System.out.println(exception.getMessage());
            fail();
        }
    }

    @Test
    public void addTask_maxCapacity_exceptionThrown() throws StorageException {
        List<Task> tasks = IntStream.rangeClosed(1, TaskList.MAX_STORAGE)
                .boxed()
                .map(num -> new Todo("mock " + num))
                .collect(Collectors.toList());
        taskList.init(tasks);
        
        try {
            Task newTask = new Todo("new todo here");
            taskList.addTask(newTask);
            fail();

        } catch (DukeException exception) {
            assertEquals(TaskList.FULL_CAPACITY_ERROR_MESSAGE, exception.getMessage());
        }
    }

    @Test
    public void deleteTask_taskNumberIsInvalid_exceptionThrown() {
        int taskNumber = 12;
        try {
            taskList.deleteTask(taskNumber);
            fail();

        } catch (DukeException exception) {
            String expected = String.format(TaskList.INVALID_TASK_ERROR_MESSAGE, taskNumber);
            assertEquals(expected, exception.getMessage());
        }
    }

    @Test
    public void deleteTask_taskListIsEmpty_exceptionThrown() throws StorageException {
        int taskNumber = 12;
        taskList.init(new ArrayList<>());
        try {
            taskList.deleteTask(taskNumber);
            fail();

        } catch (DukeException exception) {
            assertEquals(TaskList.EMPTY_LIST_ERROR_MESSAGE, exception.getMessage());
        }
    }
}
