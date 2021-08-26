package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parser_addDeadlineCommand_deadlineCommandReturned() {
        assertTrue(Parser.parse("deadline return book /by 2021-08-31 2359") instanceof AddDeadlineCommand);
    }

    @Test
    public void parser_addEventCommand_eventCommandReturned() {
        assertTrue(Parser.parse("event book convention /at 2021-08-31 2100") instanceof AddEventCommand);
    }

    @Test
    public void parser_addToDoCommand_toDoCommandReturned() {
        assertTrue(Parser.parse("todo buy groceries") instanceof AddToDoCommand);
    }

    @Test
    public void parser_completeTaskCommand_completeTaskCommandReturned() {
        assertTrue(Parser.parse("done 3") instanceof CompleteTaskCommand);
    }

    @Test
    public void parser_deleteTaskCommand_deleteTaskCommandReturned() {
        assertTrue(Parser.parse("delete 3") instanceof DeleteTaskCommand);
    }

    @Test
    public void parser_exitCommand_exitCommandReturned() {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parser_helpCommand_helpCommandReturned() {
        assertTrue(Parser.parse("help") instanceof HelpCommand);
    }

    @Test
    public void parser_helpCommand_listCommandReturned() {
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("list 2021-08-29") instanceof ListCommand);
    }

    @Test
    public void parser_unknownCommand_helpCommandReturned() {
        assertTrue(Parser.parse("blah") instanceof HelpCommand);
    }
}
