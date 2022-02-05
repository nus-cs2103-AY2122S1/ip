package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.UserInputError;
import duke.tasks.Deadline;


public class DeadlineTest {
    @Test
    public void testOutput1() throws UserInputError {
        Deadline deadline = new Deadline("merge branch", "2021-09-09 2359", true);
        assertEquals("[D][X] merge branch { by: Sep 9 2021 || 2359 }", deadline.toString());
    }

    @Test
    public void testOutput2() throws UserInputError {
        Deadline deadline = new Deadline("merge branch", "2021-09-09", false);
        assertEquals("[D][  ] merge branch { by: Sep 9 2021 }", deadline.toString());
    }

    @Test
    public void testInvalidDate1() {
        assertThrows(UserInputError.class, () ->
                new Deadline("merge branch", "2021-90-09", false));
    }

    @Test
    public void testInvalidDate2() {
        assertThrows(UserInputError.class, () ->
                new Deadline("merge branch", "next", false));
    }

    @Test
    public void testTaskToString1() throws UserInputError {
        Deadline deadline = new Deadline("merge master", "2021-09-09 0000", false);
        assertEquals("D ~#~ 0 ~#~ merge master ~#~ 2021-09-09 0000", deadline.convertTaskToString());
    }
}
