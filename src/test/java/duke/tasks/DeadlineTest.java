package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline dl = new Deadline("JUnit test iP", LocalDateTime.of(2021, 8, 26, 22, 30));

    @Test
    public void testFormatForSave() {
        assertEquals("D | 0 | JUnit test iP | 2021-08-26T22:30", dl.toSaveFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[D][ ] JUnit test iP (by: 26 Aug 2021 10.30pm)", dl.toString());
    }

}
