package duke;

import duke.exceptions.DukeException;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createToDoTask() {
        try {
            ToDo todo = new ToDo("read book");
            assertEquals(todo.toString(), "[T][ ] read book");
        } catch(DukeException e) {
            System.out.println("error");
        }
    }

    @Test
    public void createEventTask() {
        try {
            Event event = new Event("project meeting", "01/12/2021 18:00-19:00");
            assertEquals(event.toString(), "[E][ ] project meeting(at: Dec 1 2021 from 18:00 to 19:00)");
        } catch(DukeException e) {
            System.out.println("error");
        }
    }

    @Test
    public void createDeadlineTask() {
        try {
            Deadline deadline = new Deadline("return book", "02/12/2021 18:00");
            assertEquals(deadline.toString(), "[D][ ] return book(by: Dec 2 2021 18:00)");
        } catch(DukeException e) {
            System.out.println("error");
        }
    }

}
