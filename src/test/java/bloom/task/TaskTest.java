package bloom.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void newTaskTest() {
        Task t = new Task("sample");
        assertEquals("sample", t.description);
        assertFalse(t.isDone);
    }

    @Test
    public void toStringTest() {
        Task t = new Task("sample");
        assertEquals("[ ] sample", t.toString());
    }

    @Test
    public void toDbTest() {
        Task t = new Task("sample");
        assertEquals("0 | sample", t.toDb());
    }

    @Test
    public void getStatusIconTest() {
        Task t = new Task("sample");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    public void markAsDoneTest() {
        Task t = new Task("sample");
        t.markAsDone();
        assertTrue(t.isDone);
    }
}
