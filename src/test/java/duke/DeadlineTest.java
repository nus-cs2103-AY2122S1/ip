package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    LocalDate date = LocalDate.parse("2021-09-17");
    Deadline deadline = new Deadline("CS2103 iP", date);

    @Test
    public void toFileFormat_deadlineComplete_printedCorrectly() {
        deadline.setDone();
        assertEquals("D | 1 | CS2103 iP | 2021-09-17", deadline.toFileFormat());
    }

    @Test
    public void toString_deadlineComplete_printedCorrectly() {
        deadline.setDone();
        assertEquals(" [D][X] CS2103 iP (by: Sep 17 2021)", deadline.toString());
    }

}
