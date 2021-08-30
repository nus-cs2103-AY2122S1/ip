import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DeadLine;


public class DeadlineTest {
    private String task = "project submission";
    private String deadline = "2/12/2021 1635";
    private DeadLine deadlineTest = new DeadLine(task, deadline);

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
