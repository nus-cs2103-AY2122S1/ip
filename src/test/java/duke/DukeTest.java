package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import task.*;
import java.time.LocalDate;

public class DukeTest {
    @Test
    public void todoTest() {
        Todo task = new Todo("write essay");
        assertEquals("[T][ ] write essay", task.toString());
    }

    @Test
    public void deadlineTest() {
        Deadline task = new Deadline("do assignment", LocalDate.parse("2021-08-23"));
        assertEquals("[D][ ] do assignment (by: Aug 23 2021)", task.toString());
    }

    @Test
    public void eventTest() {
        Event task = new Event("wedding", LocalDate.parse("2021-08-24"));
        assertEquals("[E][ ] wedding (at: Aug 24 2021)", task.toString());
    }
}
