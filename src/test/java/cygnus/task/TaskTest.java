package cygnus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    public void stringConversion_undoneTask_success() {
        assertEquals("[ ] taskDescription", new Task("taskDescription").toString());
    }

    @Test
    public void stringConversion_doneTask_success() {
        Task task = new Task("taskDescription");
        task.setDone();
        assertEquals("[X] taskDescription", task.toString());
    }

    @Test
    public void setDone_undoneTask_success() {
        Task task = new Task("taskDescription");
        task.setDone();
        assertTrue(task.isDone);
    }

}
