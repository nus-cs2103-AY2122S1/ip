import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testToDo() {
        duke.ToDo todo = new duke.ToDo("Hi there");
        String expected = "[T][ ] Hi there";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToDoDone() {
        duke.ToDo todo = new duke.ToDo("Wash dishes");
        todo.completeTask();
        String expected = "[T][X] Wash dishes";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testEvent() {
        duke.Event event = new duke.Event("Lunch ", "2pm");
        String expected = "[E][ ] Lunch (at: 2pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventDone() {
        duke.Event event = new duke.Event("Dinner ", "9pm");
        event.completeTask();
        String expected = "[E][X] Dinner (at: 9pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testDeadline() {
        duke.Deadline deadline = new duke.Deadline("Return books ", "2021-08-24");
        String expected = "[D][ ] Return books (by: Aug 24 2021)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineDone() {
        duke.Deadline deadline = new duke.Deadline("Finish project ", "2021-11-30");
        deadline.completeTask();
        String expected = "[D][X] Finish project (by: Nov 30 2021)";
        assertEquals(expected, deadline.toString());
    }
}
