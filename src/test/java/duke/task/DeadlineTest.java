package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        assertEquals(new Deadline("sleep", "5pm").toString(), "[D][ ] sleep (by: 5pm)");
    }

    @Test
    public void formatSaveTest() {
        Deadline deadline = new Deadline("sleep", "6pm");
        assertEquals(deadline.formatSave(), "D | 0 | sleep | 6pm");
    }

}

