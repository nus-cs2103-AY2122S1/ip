package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void showTaskTest() {
        Deadline d1 = new Deadline("running", "02/09/2021 1600", false);
        assertEquals("[D][ ] running (by: Sep 02 2021, 16:00)", d1.showTask());
    }

    @Test
    public void saveTaskTest() {
        Deadline d1 = new Deadline("dancing", "02/09/2021 1800", false);
        assertEquals("D | 0 | dancing | 02/09/2021 1800", d1.saveTask());
    }
}