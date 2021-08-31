package dao;

import model.Deadline;
import model.Event;
import model.Task;
import model.ToDos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskDaoTest {
    private static TaskDaoImpl taskDao;
    
    @BeforeAll
    public static void setUpEnvironment() {
        taskDao = new TaskDaoImpl();
    }
    
    @BeforeEach
    public void setUpMethod() {
        while (taskDao.getSize() != 0) {
            taskDao.deleteTask(0);
        }
    }
    
    @Test
    @DisplayName("Adding event task should work")
    public void addTask_eventTask_taskAdded() {
        Task event = new Event("some dummy event", LocalDateTime.now());
        taskDao.addTask(event);
        assertEquals(event.toString(),
                taskDao.getTask(taskDao.getSize() - 1).toString());
    }
    
    @Test
    @DisplayName("Adding deadline task should work")
    public void addTask_deadlineTask_taskAdded() {
        Task deadline = new Deadline("some dummy deadline", LocalDateTime.now());
        taskDao.addTask(deadline);
        assertEquals(deadline.toString(),
                taskDao.getTask(taskDao.getSize() - 1).toString());
    }
    
    @Test
    @DisplayName("Adding todo task should work")
    public void addTask_todoTask_taskAdded() {
        Task todo = new ToDos("some dummy event");
        taskDao.addTask(todo);
        assertEquals(todo.toString(),
                taskDao.getTask(taskDao.getSize() - 1).toString());
    }
    
    @Test
    @DisplayName("Delete using invalid index should throw error")
    public void deleteTask_invalidIndex_exceptionThrown() {
        Task todo = new ToDos("some dummy event");
        taskDao.addTask(todo);
        int currentSize = taskDao.getSize();
        
        assertThrows(IndexOutOfBoundsException.class,
                () -> taskDao.deleteTask(currentSize + 1));
    }
    
    @Test
    @DisplayName("Delete using valid index should work")
    public void deleteTask_validIndex_deleteSuccessfully() {
        Task todo = new ToDos("some dummy event");
        taskDao.addTask(todo);
        
        int lastIndex = taskDao.getSize() - 1;
        assertDoesNotThrow(() -> taskDao.deleteTask(lastIndex));
    }
    
    @Test
    @DisplayName("Gets a single task given a valid index")
    public void getTask_validIndex_getSuccessfully() {
        Task todo = new ToDos("some dummy event");
        taskDao.addTask(todo);
        
        assertEquals(todo.toString(),
                taskDao.getTask(taskDao.getSize() - 1).toString());
    }
    
    @Test
    @DisplayName("Mark done using valid index should work")
    public void markDone_validIndex_markSuccessfully() {
        Task todo = new ToDos("some dummy event");
        taskDao.addTask(todo);
        
        taskDao.markDone(taskDao.getSize() - 1);
        assertTrue(taskDao.getTask(taskDao.getSize() - 1).isDone());
    }
    
    @Test
    @DisplayName("Gets All the List")
    public void getAll_add10Task_addedSuccessfully() {
        int listSize = 10;
        IntStream.range(0, listSize)
                .mapToObj(i -> new ToDos("some dummy event " + i))
                .forEach(todo -> taskDao.addTask(todo));
        
        assertEquals(listSize, taskDao.getSize());
    }
    
    @Test
    @DisplayName("Gets the size of Task list")
    public void getSize_add1Task_sizeOne() {
        Task todo = new ToDos("some dummy event");
        taskDao.addTask(todo);
        
        assertEquals(1, taskDao.getSize());
    }
}
