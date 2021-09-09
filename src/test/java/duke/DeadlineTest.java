package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;


public class DeadlineTest {

    private LocalDate date = LocalDate.parse("2021-09-17");
    private Deadline deadline = new Deadline("CS2103 iP", date);

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
