package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void test2() {
        boolean isRunning = Duke.processInput("bye", new TaskList());
        assertEquals(isRunning, false);
    }
}