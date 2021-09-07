package duke;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import duke.exceptions.WrongInputException;
import duke.tasks.Deadline;


public class DeadlineTest {
    @Test
    public void testDeadlineTaskCreated_success() throws WrongInputException {
        Deadline deadline1 = Deadline.createDeadline("Do Homework", "02/08/2021 1800");
        assertEquals("[D][ ] Do Homework (by: 02 Aug 2021, 18.00 PM)", deadline1.toString());

        Deadline deadline2 = Deadline.createDeadline("Submit Project", "10/08/2021 1700");
        assertEquals("[D][ ] Submit Project (by: 10 Aug 2021, 17.00 PM)", deadline2.toString());
    }
}
