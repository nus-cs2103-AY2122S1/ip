package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskTest {

    private static final String DESCRIPTION = "task description";

    private final Task undoneTask = new Task("task description");

    // Creates a done task. Ensures markAsDone is working before creating.
    private Task getDoneTask() {
        markAsDone_undoneTask_true();

        Task doneTask = new Task(DESCRIPTION);
        doneTask.markAsDone();
        return doneTask;
    }

    @Test
    void getIsDone_undoneTask_false() {
        assertFalse(undoneTask.getIsDone());
    }

    @Test
    void getIsDone_doneTask_true() {
        Task doneTask = getDoneTask();
        assertTrue(doneTask.getIsDone());
    }

    @Test
    void getDescription_undoneTask_description() {
        assertEquals(DESCRIPTION, undoneTask.getDescription());
    }

    @Test
    void getStatusIcon_undoneTask_emptySpace() {
        assertEquals(" ", undoneTask.getStatusIcon());
    }

    @Test
    void getStatusIcon_doneTask_stringX() {
        Task doneTask = getDoneTask();
        assertEquals("X", doneTask.getStatusIcon());
    }

    @Test
    void getTime_undoneTask_null() {
        assertNull(undoneTask.getTime());
    }

    @Test
    void markAsDone_undoneTask_true() {
        Task task = new Task(DESCRIPTION);
        task.markAsDone();
        assertTrue(task.getIsDone());
    }

    @Test
    void getTaskType_undoneTask_null() {
        assertNull(undoneTask.getTaskType());
    }

    @Test
    void toString_undoneTask_formattedDescription() {
        assertEquals("[ ] " + DESCRIPTION, undoneTask.toString());
    }

    @Test
    void toString_doneTask_formattedDescription() {
        Task doneTask = getDoneTask();
        assertEquals("[X] " + DESCRIPTION, doneTask.toString());
    }

    @Test
    void equals_sameTask_true() {
        assertEquals(new Task(DESCRIPTION), undoneTask);
    }

    @Test
    void equals_differentTask_false() {
        Task doneTask = new Task(DESCRIPTION);
        doneTask.markAsDone();

        Task differentDescriptionTask = new Task("other");

        assertNotEquals(undoneTask, doneTask);
        assertNotEquals(undoneTask, differentDescriptionTask);
    }

}
