package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidParamException;

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

    @Test
    public void setEvent_descriptionOfEvent_eventReturned() throws InvalidParamException {
        assertEquals("[E][ ] Finish math homework (at: Monday)",
                Event.setEvent("Finish math homework /at Monday").toString());
    }

    @Test
    public void setEvent_invalidDescriptionOfEvent_invalidParamExceptionThrown() {
        try {
            Event.setEvent("Finish math homework").toString();
            fail();
        } catch (InvalidParamException e) {
            assertEquals("Please include the time of the event after\n"
                    + "a task description using an '/at' (only once).\n"
                    + "i.e. event project meeting /at Aug 6th 2-4pm",
                    e.getMessage());
        }
    }
}
