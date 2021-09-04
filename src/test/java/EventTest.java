import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Event;

public class EventTest {
    @Test
    public void toString_normal() throws Exception {
        Event todo = new Event("task", "2021-08-21");
        Assertions.assertEquals("[E][ ] task (at: Aug 21 2021)", todo.toString());
    }

    @Test
    public void constructor_emptyDescription_dukeException() {
        Throwable exception = Assertions.assertThrows(DateTimeParseException.class, () -> new Event("task", ""));
        Assertions.assertEquals("Text '' could not be parsed at index 0", exception.getMessage());
    }
}
