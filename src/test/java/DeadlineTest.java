import duke.DeadLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    String task = "project submission";
    String deadline = "2/12/2021 1635";
    DeadLine deadlineTest = new DeadLine(task, deadline);

    @Test
    public void timeFormChangeTest() {
        String actual = deadlineTest.formatChanger();
        String expected = "Dec 02 2021, 4.35 PM";
        assertEquals(actual, expected);
    }
    @Test
    public void printTaskTest() {
        String actual = deadlineTest.printTask();
        String expected = "[D][ ] project submission (by: Dec 02 2021, 4.35 PM)";
        assertEquals(actual, expected);
    }
}
