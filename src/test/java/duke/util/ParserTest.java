package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.InvalidInputException;

public class ParserTest {
    @Test
    public void parse_validTodo_createsTodoCommand() throws InvalidInputException {
        assertEquals(Parser.parse(new String[]{"todo", "Action"}), new TodoCommand("Action"));
    }

    @Test
    public void parse_validDeadline_createsDeadlineCommand() throws InvalidInputException {
        assertEquals(Parser.parse(new String[]{"deadline", "Action"}), new DeadlineCommand("Action"));
    }

    @Test
    public void parse_validEvent_createsEventCommand() throws InvalidInputException {
        assertEquals(Parser.parse(new String[]{"event", "Action"}), new EventCommand("Action"));
    }

    @Test
    public void parse_validList_createsListCommand() throws InvalidInputException {
        assertEquals(Parser.parse(new String[]{"list", ""}), new ListCommand());
    }

    @Test
    public void parse_invalidCommand_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            Parser.parse(new String[]{"hi", "Action"});
        });
    }
}
