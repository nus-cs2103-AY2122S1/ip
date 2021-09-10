package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline task = new Deadline("return book", "23/08/2021 2000");
        assertEquals("[D][✗] return book (by: Aug 23 2021 20:00)", task.toString());
    }

    @Test
    public void testSaveAsString() {
        Deadline task = new Deadline(CompletionStatus.COMPLETED, "return book", "23/08/2021 2000");
        assertEquals("D | 1 | return book | 2021-08-23 20:00", task.convertToString());
    }

    @Test
    public void testGetStatusIcon() {
        Deadline task = new Deadline(CompletionStatus.COMPLETED, "return book", "23/08/2021 2000");
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    public void testGetDate() {
        LocalDate date = LocalDate.of(2021, 9, 4);
        Deadline task = new Deadline("return book", "04/09/2021 2000");
        assertEquals(date, task.getDate());
    }

    @Test
    public void testGetTime() {
        LocalTime time = LocalTime.of(20, 0);
        Deadline task = new Deadline("return book", "04/09/2021 2000");
        assertEquals(time, task.getTime());
    }

    @Test
    public void testMarkAsDone() {
        Deadline task = new Deadline("return book", "23/08/2021 2000");
        task.markTaskAsDone();
        assertEquals("[D][✓] return book (by: Aug 23 2021 20:00)", task.toString());
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
