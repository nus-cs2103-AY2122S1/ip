package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.Todo;

class TaskTest {

    @Test
    void getStatusIcon_completedTask_success() {
        Task task = new Todo("make coffee", true);
        assertEquals("[X]", task.getStatusIcon());
    }

    @Test
    void getStatusIcon_incompleteTask_success() {
        Task task = new Todo("make lunch", false);
        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    void getTag_todoTask_success() {
        Task task = new Todo("make lunch", false);
        assertEquals("T", task.getTag());
    }

    @Test
    void getDescription_todoTask_success() {
        Task task = new Todo("make lunch", false);
        assertEquals("make lunch", task.getDescription());
    }
}