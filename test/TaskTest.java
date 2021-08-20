import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains JUnit5 tests for public members of duke.task.Task class.
 */
class TaskTest {

    @Test
    void getStatusIcon_undoneTask_uncheckedBox() {
        Task task = new Task("duke.task.Task");

        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    void markAsDone_undoneTask_checkedBox() {
        Task task = new Task("duke.task.Task");

        task.markAsDone();

        assertEquals("[X]", task.getStatusIcon());
    }

    @Test
    void toString_undoneTask_descriptionWithCheckedBox() {
        Task task = new Task("duke.task.Task");

        assertEquals("[ ] duke.task.Task", task.toString());
    }

    @Test
    void toString_doneTask_descriptionWithUncheckedBox() {
        Task task = new Task("duke.task.Task");

        task.markAsDone();

        assertEquals("[X] duke.task.Task", task.toString());
    }
}