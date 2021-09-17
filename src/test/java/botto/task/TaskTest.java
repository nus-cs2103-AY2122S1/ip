package botto.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testStringConversion() {
        Task task1 = new Task("read book");
        assertEquals("[ ] read book", task1.toString());
        Task task2 = new Task("return book");
        assertEquals("[ ] return book", task2.toString());
    }

    @Test
    public void getStatusIcon_someSamples_success() {
        Task task1 = new Task("read book");
        assertEquals(" ", task1.getStatusIcon());
        task1.isDone = true;
        assertEquals("X", task1.getStatusIcon());
        Task task2 = new Task("return book");
        assertEquals(" ", task2.getStatusIcon());
        task2.isDone = true;
        assertEquals("X", task2.getStatusIcon());
    }

    @Test
    public void markAsDone_someSamples_success() {
        Task task1 = new Task("read book");
        task1.markAsDone();
        assertTrue(task1.isDone);
        Task task2 = new Task("return book");
        assertFalse(task2.isDone);
        task2.markAsDone();
        assertTrue(task2.isDone);
    }

}
