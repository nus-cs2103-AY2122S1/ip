package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testToDoStringConversion() {
        assertEquals("[T][X] 1 ", ToDo.of("1", true).toString());
    }

    @Test
    public void testDeadlineStringConversion() {
        assertEquals("[D][ ] 2 (by: Sunday, 20 February 2022, 01:45 PM)", Deadline.of("2","20/02/2022 1345").toString());
    }

    @Test
    public void testEventStringConversion() {
        assertEquals("[E][ ] 3 (at: Monday 2-4pm)", Event.of("3","Monday 2-4pm").toString());
    }

}
