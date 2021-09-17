package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

public class DukeTest {
    protected static final String TEST = "TEST";
    protected static final String TEST_TASK_TYPE = "L";
    @Test
    public void parseTestSuccess() {
        Parser parser = new Parser();
        Task t = new Task(TEST) {
            @Override
            protected String getTaskType() {
                return TEST_TASK_TYPE;
            }
        };
        Exception exception = null;
        String actual = null;
        String expected = TEST_TASK_TYPE + "|false|" + TEST;
        try {
            actual = parser.encodeTask(t);
        } catch (DukeNoDateException e) {
            exception = e;
        }
        assertTrue(exception == null);
        assertEquals(expected, actual);
    }

    @Test
    public void createDeadlineObject() throws ParseException {
        String deadlineCommad = TEST + " /by 1/1/2021";
        Deadline d = null;
        DukeDateParseException exception = null;
        try {
            d = new Deadline(deadlineCommad);
        } catch (DukeDateParseException e) {
            exception = e;
        }
        assertTrue(exception == null);
        String expected = "[D][ ] " + TEST + " (by: "
                + new SimpleDateFormat("dd/mm/yyyy").parseObject("1/1/2021") + ")";
        assertEquals(expected, d.toString());
    }
}
