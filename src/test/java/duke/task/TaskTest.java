package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "G|i|task 1|",
                new Task("task 1").getSaveFormat()
        );
        assertEquals(
                "A|i|task 1|",
                new Task("task 1", "A").getSaveFormat()
        );
        assertEquals(
                "K|c|task 1|",
                new Task("task 1", true, "K", new String[Task.MAX_TAGS]).getSaveFormat()
        );
    }

    @Test
    public void getName_emptyInput_correctName() {
        assertEquals(
                "task 1",
                new Task("task 1").getName()
        );
        assertEquals(
                "task 1",
                new Task("task 1", "A").getName()
        );
        assertEquals(
                "task 1",
                new Task("task 1", true, "K", new String[Task.MAX_TAGS]).getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertTrue(
                new Task("task 1").completeTask()
        );
        assertTrue(
                new Task("task 1", "A").completeTask()
        );
        assertTrue(
                new Task("task 1", true, "K", new String[Task.MAX_TAGS]).completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertFalse(
                new Task("task 1").hasCompleted()
        );
        assertFalse(
                new Task("task 1", "A").hasCompleted()
        );
        assertTrue(
                new Task("task 1", true, "K", new String[Task.MAX_TAGS]).hasCompleted()
        );

        Task task = new Task("task 1");
        task.completeTask();
        assertTrue(task.hasCompleted());
    }

    @Test
    public void getTaskType_emptyInput_stringD() {
        assertEquals(
                "G",
                new Task("task 1").getTaskType()
        );
        assertEquals(
                "A",
                new Task("task 1", "A").getTaskType()
        );
        assertEquals(
                "K",
                new Task("task 1", true, "K", new String[Task.MAX_TAGS]).getTaskType()
        );
    }

    @Test
    public void getTags_emptyInput_correctString() {
        Task task = new Task("task 1", "T");
        assertEquals("", task.getTags());

        task.addTag("tag1");
        assertEquals("tag1", task.getTags());

        task.addTag("tag2");
        assertEquals("tag1, tag2", task.getTags());
    }
}
