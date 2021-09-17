package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

public class ParserTest {
    /**
     * Test the parse method
     * @throws DukeException
     */
    @Test
    public void testParse() throws DukeException {
        Command deadlineActual = Parser.parse("deadline gym /by 25/8/2021 0800");
        DeadlineCommand deadlineCommand = new DeadlineCommand("gym", "25/8/2021 0800");
        assertEquals(deadlineCommand.toString(), deadlineActual.toString());

        Command deleteActual = Parser.parse("delete 1");
        DeleteCommand deleteCommand = new DeleteCommand("1");
        assertEquals(deleteCommand.toString(), deleteActual.toString());

        Command doneActual = Parser.parse("done 1");
        DoneCommand doneCommand = new DoneCommand("1");
        assertEquals(doneCommand.toString(), doneActual.toString());

        Command eventActual = Parser.parse("event gym /at 25/8/2021 0800");
        EventCommand eventCommand = new EventCommand("gym", "25/8/2021 0800");
        assertEquals(eventCommand.toString(), eventActual.toString());

        Command exitActual = Parser.parse("bye");
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(exitCommand.toString(), exitActual.toString());

        Command listActual = Parser.parse("list");
        ListCommand listCommand = new ListCommand();
        assertEquals(listCommand.toString(), listActual.toString());

        Command todoActual = Parser.parse("todo something");
        TodoCommand todoCommand = new TodoCommand("something");
        assertEquals(todoCommand.toString(), todoActual.toString());
    }
}
