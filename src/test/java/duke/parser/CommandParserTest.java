package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

public class CommandParserTest {
    @Test
    public void parser_addDeadlineCommand_deadlineCommandReturned() {
        assertTrue(CommandParser.parse("deadline return book /by 2021-08-31 2359") instanceof AddDeadlineCommand);
    }

    @Test
    public void parser_addEventCommand_eventCommandReturned() {
        assertTrue(CommandParser.parse("event book convention /at 2021-08-31 2100") instanceof AddEventCommand);
    }

    @Test
    public void parser_addToDoCommand_toDoCommandReturned() {
        assertTrue(CommandParser.parse("todo buy groceries") instanceof AddToDoCommand);
    }

    @Test
    public void parser_completeTaskCommand_completeTaskCommandReturned() {
        assertTrue(CommandParser.parse("done 3") instanceof CompleteTaskCommand);
    }

    @Test
    public void parser_deleteTaskCommand_deleteTaskCommandReturned() {
        assertTrue(CommandParser.parse("delete 3") instanceof DeleteTaskCommand);
    }

    @Test
    public void parser_exitCommand_exitCommandReturned() {
        assertTrue(CommandParser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parser_helpCommand_helpCommandReturned() {
        assertTrue(CommandParser.parse("help") instanceof HelpCommand);
    }

    @Test
    public void parser_helpCommand_listCommandReturned() {
        assertTrue(CommandParser.parse("list") instanceof ListCommand);
        assertTrue(CommandParser.parse("list 2021-08-29") instanceof ListCommand);
    }

    @Test
    public void parser_unknownCommand_helpCommandReturned() {
        assertTrue(CommandParser.parse("blah") instanceof HelpCommand);
    }
}
