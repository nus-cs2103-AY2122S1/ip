import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void testToString_testNoDateNoTimeHaveText() {
        Deadline testTask = new Deadline("testDeadline", "due whenever");
        assertEquals("[D][ ] testDeadline (by: due whenever)", testTask.toString());
    }

    @Test
    public void testToString_testHaveDateNoTimeNoText() {
        Deadline testTask = new Deadline("testDeadline", "2022-04-02");
        testTask.markAsDone();
        assertEquals("[D][X] testDeadline (by: Apr 2 2022)", testTask.toString());
    }

    @Test
    public void testToString_testHaveDateInvalidTimeNoText() {
        Deadline testTask = new Deadline("testDeadline", "2022-04-02 2400");
        assertEquals("[D][ ] testDeadline (by: Apr 2 2022 2400)", testTask.toString());
    }

    @Test
    public void testToSaveFormat_testInvalidDateInvalidTimeHaveText() {
        Deadline testTask = new Deadline("testDeadline", "2022-04-31 2400 test text");
        testTask.markAsDone();
        assertEquals("D|1|testDeadline|2022-04-31 2400 test text", testTask.convertToSaveFormat());
    }

    @Test
    public void testToSaveFormat_testHaveDateHaveTimeNoText() {
        Deadline testTask = new Deadline("testDeadline", "2022-04-30 2359");
        assertEquals("D|0|testDeadline|Apr 30 2022 11:59pm", testTask.convertToSaveFormat());
    }

    @Test
    public void testToSaveFormat_testHaveDateHaveTimeHaveText() {
        Deadline testTask = new Deadline("testDeadline", "2022-04-30 0000 test text");
        testTask.markAsDone();
        assertEquals("D|1|testDeadline|Apr 30 2022 12:00am test text", testTask.convertToSaveFormat());
    }
}
