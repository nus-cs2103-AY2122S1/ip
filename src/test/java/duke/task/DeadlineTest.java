package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void toString_descriptionDeadline_DeadlineStringReturned() throws DukeException {
        assertEquals("[D][ ] do iP (by: 28 Aug 2021 2359) \n",
                new Deadline("do iP", "28-8-2021 2359").toString());
    }

    @Test
    public void deadlineConstructor_invalidTimeFormat_DukeExceptionThrown() {
        try {
            Deadline dl = new Deadline("do iP", "28-8-2021");
        } catch (DukeException e) {

        }
    }
}
