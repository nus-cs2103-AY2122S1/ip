package pix.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    @Test
    void getTaskName() {
        Todo task = new Todo("borrow book");
        assertEquals("borrow book", task.getTaskName());
        assertEquals(false, task.isDone());
    }

    @Test
    void getTaskCompleteness() {
        Todo task = new Todo("read book");
        assertEquals(false, task.isDone());
        task.completeTask();
        assertEquals(true, task.isDone());
    }
}