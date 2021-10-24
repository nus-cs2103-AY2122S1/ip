package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void createTodo() {
        try {
            TodoCommand command = new TodoCommand("todo run");
            assertEquals("run", command.getTodo().getDesription());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void createEvent() {
        EventCommand command = new EventCommand("event football /at 2012-03-03");
        assertEquals("[E][ ] football (at: Mar 3 2012)", command.getEvent().toString());
    }

    @Test
    public void createDeadline() {
        DeadlineCommand command = new DeadlineCommand("deadline essay /by 2012-03-03");
        assertEquals("[D][ ] essay (by: Mar 3 2012)", command.getDeadline().toString());
    }
}
