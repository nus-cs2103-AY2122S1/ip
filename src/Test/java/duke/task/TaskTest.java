package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testGetStatusIcon() {
        Task task = new Task("Do homework");
        assertEquals(" ", task.getStatusIcon());

        task.setIsDone(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void testGetIsDone() {
        Task task = new Task("getIsDone test");
        assertEquals(false, task.getIsDone());

        task.setIsDone(true);
        assertEquals(true, task.getIsDone());
    }

    @Test
    void testToString() {
        Task task = new Task("testToString");
        assertEquals("[ ] testToString", task.toString());

        task.setIsDone(true);
        assertEquals("[X] testToString", task.toString());
    }

    @Test
    void testSaveToFile() {
        Task task = new Task("testSaveToFile");
        assertEquals("| 0 | testSaveToFile", task.toFileStringFormat());

        task.setIsDone(true);
        assertEquals("| 1 | testSaveToFile", task.toFileStringFormat());
    }
}
