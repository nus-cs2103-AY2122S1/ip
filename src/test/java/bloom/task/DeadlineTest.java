package bloom.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void newDeadlineTest() {
        LocalDateTime datetime = LocalDateTime.of(2021, 1, 1, 12, 0, 0);
        Deadline d = new Deadline("sample", datetime);
        assertEquals("sample", d.description);
        assertFalse(d.isDone);
        assertEquals(datetime, d.by);
    }

    @Test
    public void toStringTest() {
        LocalDateTime datetime = LocalDateTime.of(2021, 1, 1, 12, 0, 0);
        Deadline d = new Deadline("sample", datetime);
        assertEquals("[D][ ] sample (by: Jan 1 2021 12:0)", d.toString());
    }

    @Test
    public void toDbTest() {
        LocalDateTime datetime = LocalDateTime.of(2021, 1, 1, 12, 0, 0);
        Deadline d = new Deadline("sample", datetime);
        assertEquals("D | 0 | sample | 2021-01-01T12:00", d.toDb());
    }
}
