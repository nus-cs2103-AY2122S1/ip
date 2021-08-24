import duke.Deadline;
import duke.ToDo;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

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
