package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline task = new Deadline("return book", "23/08/2021 2000");
        assertEquals("[D][ ] return book (by: Aug 23 2021 20:00)", task.toString());
    }

    @Test
    public void testSaveAsString() {
        Deadline task = new Deadline("1", "return book", "23/08/2021 2000");
        assertEquals("D | 1 | return book | 2021-08-23 20:00", task.saveAsString());
    }

    @Test
    public void testGetStatusIcon() {
        Deadline task = new Deadline("1", "return book", "23/08/2021 2000");
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        Deadline task = new Deadline("return book", "23/08/2021 2000");
        task.markTaskAsDone();
        assertEquals("[D][X] return book (by: Aug 23 2021 20:00)", task.toString());
    }

    @Test
    public void testOnDate_sameDate() {
        Deadline task = new Deadline("return book", "23/08/2021 2000");
        assertEquals(true, task.isOnDate("2021-08-23"));
    }

    @Test
    public void testOnDate_differentDate() {
        Deadline task = new Deadline("return book", "23/08/2021 2000");
        assertEquals(false, task.isOnDate("2021-08-21"));
    }
}
