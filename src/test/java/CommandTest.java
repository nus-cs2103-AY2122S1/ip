
import Wonderland.DukeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import Wonderland.task.TaskList;
import Wonderland.Command;

import java.time.LocalDate;
import java.util.ArrayList;

public class CommandTest {
    @Test
    public void deadline_emptyDescription_exceptionThrown() {
        String description = "";
        String by = "2021-09-11";
        TaskList tasks = new TaskList(new ArrayList<>());
        Command command = new Command();

        String output = "";
        try {
            command.deadline(description, by, tasks);
        } catch (DukeException e) {
            output = e.toString().trim();
        }
        assertEquals("OOPS!!! The description of a deadline cannot be empty.", output);
    }

    @Test
    public void deadline_validInput() {
        String description = "deadline 1";
        String by = "2021-09-11";
        Command command = new Command();
        String output = "";
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            output = command.deadline(description, by, new TaskList(new ArrayList<>()));
        } catch (DukeException e) {
        }
        assertEquals(tasks.addDeadline(description, LocalDate.parse(by.trim())), output);
    }
}
