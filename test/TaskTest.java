import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains JUnit5 tests for public members of Task class.
 */
class TaskTest {

    @Test
    void getStatusIcon_undoneTask_uncheckedBox() {
        Task task = new Task("Task");

        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    void markAsDone_undoneTask_checkedBox() {
        Task task = new Task("Task");

        task.markAsDone();

        assertEquals("[X]", task.getStatusIcon());
    }

    @Test
    void toString_undoneTask_descriptionWithCheckedBox() {
        Task task = new Task("Task");

        assertEquals("[ ] Task", task.toString());
    }

    @Test
    void toString_doneTask_descriptionWithUncheckedBox() {
        Task task = new Task("Task");

        task.markAsDone();

        assertEquals("[X] Task", task.toString());
    }
}