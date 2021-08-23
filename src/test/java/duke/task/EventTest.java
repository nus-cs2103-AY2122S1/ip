package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_descriptionOfEvent_eventStringReturned() {
        assertEquals("[E][ ] Biology Exam (at: August 12 7-10am)",
                new Event("Biology Exam", "August 12 7-10am").toString());
    }

    @Test
    public void getAt_timeOfEvent_eventTimeReturned() {
        assertEquals("Monday 2-4pm",
                new Event("Project Meeting", "Monday 2-4pm").getAt());
    }
}
