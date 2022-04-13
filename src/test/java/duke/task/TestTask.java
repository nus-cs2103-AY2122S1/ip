package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestTask {

    /**
     * Test the functionality of Task abstract classes and its abstract methods
     */
    @Test
    public void testTask() {
        Task stubTask = new Task("stub") {
            @Override
            public Task completedTask() {
                markAsCompleted();
                return this;
            }
            @Override
            public String save() {
                return "save task";
            }

            @Override
            public String getDate() {
                return "no date";
            }
        };
        assertFalse(stubTask.isCompleted());
        assertEquals("[ ] stub", stubTask.toString());
        stubTask.markAsCompleted();
        assertTrue(stubTask.isCompleted());
        assertEquals("[X] stub", stubTask.toString());
        assertEquals("save task", stubTask.save());
        assertEquals("no date", stubTask.getDate());

        Task stubTask2 = new Task("temp2") {
            @Override
            public Task completedTask() {
                markAsCompleted();
                return this;
            }

            @Override
            public String save() {
                return null;
            }

            @Override
            public String getDate() {
                return null;
            }
        };
        assertEquals("[X] temp2", stubTask2.completedTask().toString());
        assertTrue(stubTask2.contains("emp"));
        assertFalse(stubTask2.contains("stub"));
    }
}
