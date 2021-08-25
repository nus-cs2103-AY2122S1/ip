package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    String description = "read";
    LocalDate date = LocalDate.parse("2021-08-08");
    String time = "1800";
    Task deadline = new Deadline(description, date, time);
    Task todo = new ToDo(description);
    Task event = new Event(description, date);

    @Test
    public void logoTest() {
        assertEquals("[D]", deadline.logo());
        assertEquals("[E]", event.logo());
        assertEquals("[T]", todo.logo());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] read (by: Aug 8 2021 1800)", deadline.toString());
        assertEquals("[E][ ] read (at: Aug 8 2021 )", event.toString());
        assertEquals("[T][ ] read", todo.toString());
    }
}
