package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void getAt_validInput_success() {
        Event event1 = new Event("return book", "02/10/2021 1400");
        Event event2 = new Event("meeting", "19/09/2021");
        LocalDateTime time1 = LocalDateTime.of(2021, 10, 2, 14, 0);
        LocalDateTime time2 = LocalDate.of(2021, 9, 19).atStartOfDay();
        assertEquals(time1, event1.getAt());
        assertEquals(time2, event2.getAt());
    }

    @Test
    void createEvent_invalidInput_errorThrow() {
        try {
            String descriptionInput = "meeting";
            String timeInput = "21 09 2021 1200";
            new Event(descriptionInput, timeInput);
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("OOPS!!! The time is not of the correct format!"));
        }
    }

    @Test
    void testToString() {
        Event event1 = new Event("submit", "21/09/2021 1200");
        Event event2 = new Event("meeting", "23/09/2021");
        event1.markAsHighPriority();
        event2.markAsDone();
        String output1 = "[E][  ] \u2605\u2605\u2605submit (at: 12:00, 21/09/2021)";
        String output2 = "[E][\u2713] \u2605\u2605meeting (at: 23/09/2021)";
        assertEquals(output1, event1.toString());
        assertEquals(output2, event2.toString());
    }
}
