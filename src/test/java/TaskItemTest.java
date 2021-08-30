import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.ToDo;

public class TaskItemTest {

    @Test
    public void testStringRepresentationForDeadline() {
        Deadline dead = new Deadline(" kill rick and morty ", LocalDateTime.parse("2021-08-28T12:00"));
        assertEquals("[D][ ] kill rick and morty (by: SATURDAY)", dead.toString());
    }

    @Test
    public void testStringRepresentationForToDo() {
        ToDo something = new ToDo(" kill rick and morty");
        assertEquals("[T][ ] kill rick and morty", something.toString());
    }
}
