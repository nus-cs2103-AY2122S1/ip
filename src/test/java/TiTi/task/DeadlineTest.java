package titi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void basicTest() {
        Deadline testDeadline = new Deadline("homework", "Mon 5pm");
        assertEquals(testDeadline.toString(), "[D][ ] homework (by: Mon 5pm)");
    }

    @Test
    public void dateTest() {
        Deadline testDeadline = new Deadline("script", "2021-08-31");
        assertEquals(testDeadline.toString(), "[D][ ] script (by: Aug 31 2021)");
    }

}
