import org.junit.jupiter.api.Test;

import addon.Tasklist;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class
 */
public class TasklistTest {

    @Test
    public void deadlineString_homeworkDetails_success(){
        Tasklist.Deadline testDeadline = new Tasklist.Deadline("homework", LocalDateTime.of(2021,
                8,24,0,0));
        assertEquals(testDeadline.toString(), "[D][ ] homework (by: AUG 24 2021 @ 0000)");
    }
    @Test
    public void eventString_napDetails_success(){
        Tasklist.Event testEvent = new Tasklist.Event("nap", LocalDateTime.of(2021,7,12,
                15,0));
        testEvent.markDone();
        assertEquals(testEvent.toString(), "[E][X] nap (at: JUL 12 2021 @ 1500)");
    }
    @Test
    public void todoString_blinkDetails_success() {
        Tasklist.Todo testDoto = new Tasklist.Todo("blink");
        testDoto.markDone();
        assertEquals(testDoto.toString(), "[T][X] blink");
    }// not sure how to test souts
}
