package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void showEventTest1() {
        assertEquals("project meeting (at: Aug 26 2021, 1500)",
                new Event("project meeting /at 26/8/2021 1500").showFullDescription());
    }

    @Test
    public void showEventTest2() {
        assertEquals("meet my friends (at: Sep 4 2021, 1900)",
                new Event("meet my friends /at 2021-09-04 1900").showFullDescription());
    }
}
