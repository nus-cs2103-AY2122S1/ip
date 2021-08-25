package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void showDeadlineTest1() {
        assertEquals("wash the dishes (by: Feb 3 2021, 1800)", new Deadline("wash the dishes /by 2021-02-03 1800").showTask());
    }

    @Test
    public void showDeadlineTest2() {
        assertEquals("return book (by: Aug 27 2021, 2100)", new Deadline("return book /by 27/8/2021 2100").showTask());
    }
}
