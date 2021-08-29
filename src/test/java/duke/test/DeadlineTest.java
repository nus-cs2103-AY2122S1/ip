package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final Deadline deadlineIncomplete = new Deadline("test 1", false,
            LocalDate.parse("31/12/2021", dtf));
    private final Deadline deadlineComplete = new Deadline("test 2", true,
            LocalDate.parse("01/08/2021", dtf));
    private final Deadline deadlineDifferentDateFormat = new Deadline("test 3", false,
            LocalDate.of(2021, 9, 1));
    @Test
    public void toString_incompleteDeadline_success() {
        assertEquals("[D][] test 1 (by: 31 Dec 2021)", deadlineIncomplete.toString());
    }

    @Test
    public void toString_completeDeadline_success() {
        assertEquals("[D][X] test 2 (by: 01 Aug 2021)", deadlineComplete.toString());
    }

    @Test
    public void toString_differentDateFormat_success() {
        assertEquals("[D][] test 3 (by: 01 Sep 2021)", deadlineDifferentDateFormat.toString());
    }
}
