import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.util.DukeException;

public class TaskTest {

    @Test
    public void createEventTest() {
        assertEquals(new Event("test event", LocalDate.of(2020, 1, 1)),
                Event.create("event test event /on 01/01/2020"));

        Exception exception = assertThrows(
                DukeException.class, () -> Event.create("event test event"));
        assertEquals("the [/on] time of event cannot be empty",
                exception.getMessage());

        exception = assertThrows(
                DukeException.class, () -> Event.create("event"));
        assertEquals("the description of event cannot be empty",
                exception.getMessage());

        exception = assertThrows(
                DukeException.class, () -> Event.create("test event"));
        assertEquals("I can't seem to find the event keyword",
                exception.getMessage());
    }

    @Test
    public void createDeadlineTest() {
        assertEquals(new Deadline("test deadline", LocalDate.of(2020, 1, 1)),
                Deadline.create("deadline test deadline /by 01/01/2020"));

        Exception exception = assertThrows(
                DukeException.class, () -> Deadline.create("deadline test deadline"));
        assertEquals("the [/by] time of deadline cannot be empty",
                exception.getMessage());

        exception = assertThrows(
                DukeException.class, () -> Deadline.create("deadline /by 20/20/1000"));
        assertEquals("the description of deadline cannot be empty",
                exception.getMessage());

        exception = assertThrows(
                DukeException.class, () -> Deadline.create("test event"));
        assertEquals("I can't seem to find the deadline keyword",
                exception.getMessage());
    }

    @Test
    public void createToDoTest() {
        assertEquals(new ToDo("test todo"),
                ToDo.create("todo test todo"));

        Exception exception = assertThrows(
                DukeException.class, () -> ToDo.create("todo"));
        assertEquals("the description of todo cannot be empty",
                exception.getMessage());

        exception = assertThrows(
                DukeException.class, () -> ToDo.create("test todo"));
        assertEquals("I can't seem to find the todo keyword",
                exception.getMessage());
    }

}
