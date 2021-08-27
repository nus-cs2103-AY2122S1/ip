package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import side.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void toStringTestNoTime() {
        Deadline d = new Deadline("Test", "2020-11-11");
        assertEquals(d.toString(), "[D][ ] Test (by: 11 Nov 2020)");
    }

    @Test
    public void toStringTestTime() {
        Deadline d = new Deadline("Test", "2020-11-11, 1800");
        assertEquals(d.toString(), "[D][ ] Test (by: 11 Nov 2020, 6:00:00 PM)");
    }
}
