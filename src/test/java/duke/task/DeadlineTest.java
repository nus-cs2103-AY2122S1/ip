package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidParamException;

public class DeadlineTest {
    @Test
    public void toString_descriptionOfDeadline_deadlineStringReturned() throws InvalidParamException {
        assertEquals("[D][ ] Return book (by: Jan 02 2025)",
                new Deadline("Return book", "2025-01-02").toString());
    }

    @Test
    public void getBy_dateOfDeadline_DeadlineDateReturned() throws InvalidParamException {
        assertEquals("2021-12-25",
                new Deadline("Finish math homework", "2021-12-25").getBy());
    }

    @Test
    public void deadlineConstructor_invalidDate_invalidParamExceptionThrown() {
        try {
            new Deadline("Finish math homework", "Tuesday");
            fail();
        } catch (InvalidParamException e) {
            assertEquals("\n\nThe deadline should be a valid date in the form: yyyy-mm-dd\n" + "i.e. 2021-12-25",
                    e.getMessage());
        }
    }
}
