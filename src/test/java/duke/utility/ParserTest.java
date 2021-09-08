package duke.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;

public class ParserTest {

    @Test
    public void parseCommand_invalidCommands_exceptionThrown() {
        Parser p = new Parser(new TaskList(), null);
        assertEquals("Please type some commands!", p.parseCommand(""));
        assertEquals("Please type some commands!", p.parseCommand("  "));
        assertEquals("Invalid task description: missing details!", p.parseCommand("event"));
        assertEquals("Invalid input for delete command. Please enter [delete] followed by the number of"
                + " the line to delete\ne.g. delete 2", p.parseCommand("delete abc"));
        assertEquals("Invalid command!", p.parseCommand("beans"));
        assertEquals("Invalid task description: "
                + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr "
                + "format)]\ne.g. event lecture / 21-02-2021 1500", p.parseCommand("deadline essay / 12 Nov 1998 "
                + "1234"));
    }

    @Test
    public void parseCommand_modifyNonExistentItems_exceptionThrown() {
        Task t = new ToDo("code");
        List<Task> l = new ArrayList<Task>();
        l.add(t);
        TaskList tl = new TaskList(l);
        Parser p = new Parser(tl, null);
        assertEquals("Task is not in list!", p.parseCommand("done sleep"));
        assertEquals("Task number 3 does not exist!", p.parseCommand("delete 3"));
    }
}
