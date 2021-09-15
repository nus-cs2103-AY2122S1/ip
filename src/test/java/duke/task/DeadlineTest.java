package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    private static final String DESCRIPTION = "task description";

    // yyyy-mm-dd format.
    private static final LocalDate TIME = LocalDate.parse("2020-01-01");

    private final Deadline deadline = new Deadline(DESCRIPTION, TIME);

    @Test
    void getTaskType_deadline_stringD() {
        assertEquals("D", deadline.getTaskType());
    }

    @Test
    void getTime_deadline_localDate() {
        assertEquals(TIME, deadline.getTime());
    }

    @Test
    void toString_deadline_formattedDescription() {
        assertEquals("[D][ ] " + DESCRIPTION + " (by: 1 January 2020)", deadline.toString());
    }

    @Test
    void equals_sameDeadline_true() {
        assertEquals(deadline, new Deadline(DESCRIPTION, TIME));
    }

    @Test
    void equals_differentDeadline_false() {
        LocalDate otherLocalDate = LocalDate.parse("2020-02-02");

        Deadline doneDeadline = new Deadline(DESCRIPTION, TIME);
        doneDeadline.markAsDone();

        Deadline differentTimeDeadline = new Deadline(DESCRIPTION, otherLocalDate);

        Deadline differentDescriptionDeadline = new Deadline("other", TIME);

        assertNotEquals(deadline, doneDeadline);
        assertNotEquals(deadline, differentTimeDeadline);
        assertNotEquals(deadline, differentDescriptionDeadline);
    }
}
