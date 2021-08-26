package Duke.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    @Test
    public void testTask() {
        Task stubTask = new Task("stub") {
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
    }
}
