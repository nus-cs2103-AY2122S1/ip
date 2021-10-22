package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


class DeadlineTest {

    @Test
    void getBy_validInput_success() {
        Deadline deadline1 = new Deadline("submit", "21/09/2021 1200");
        Deadline deadline2 = new Deadline("meeting", "23/09/2021");
        LocalDateTime time1 = LocalDateTime.of(2021, 9, 21, 12, 0);
        LocalDateTime time2 = LocalDate.of(2021, 9, 23).atStartOfDay();
        assertEquals(time1, deadline1.getBy());
        assertEquals(time2, deadline2.getBy());
    }

    @Test
    void createDeadline_invalidInput_errorThrow() {
        try {
            String descriptionInput = "meeting";
            String timeInput = "21 09 2021 1200";
            new Deadline(descriptionInput, timeInput);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("OOPS!!! The time is not of the correct format!"));
        }
    }

    @Test
    void testToString() {
        Deadline deadline1 = new Deadline("submit", "21/09/2021 1200");
        Deadline deadline2 = new Deadline("meeting", "23/09/2021");
        deadline1.markAsHighPriority();
        deadline2.markAsDone();
        String output1 = "[D][  ] \u2605\u2605\u2605submit (by: 12:00, 21/09/2021)";
        String output2 = "[D][\u2713] \u2605\u2605meeting (by: 23/09/2021)";
        assertEquals(output1, deadline1.toString());
        assertEquals(output2, deadline2.toString());
    }
}
