package duke;

import duke.exceptions.WrongInputException;
import duke.tasks.Event;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void  testEventTaskCreated_success() throws WrongInputException {
        Event event1 = Event.createEvent("Cycling", "02/08/2021 1800");
        assertEquals("[E][ ] Cycling (at: 02 Aug 2021, 18.00 PM)", event1.toString());

        Event event2 = Event.createEvent("Eat", "10/08/2021 1700");
        assertEquals("[E][ ] Eat (at: 10 Aug 2021, 17.00 PM)", event2.toString());
    }
}
