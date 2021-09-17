package duke.command;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommandsTest {
    @Test
    void findCommand() {
        assertTrue(Commands.findCommand("deadline") instanceof DeadlineCommand);
        assertTrue(Commands.findCommand("delete") instanceof DeleteCommand);
        assertTrue(Commands.findCommand("done") instanceof DoneCommand);
        assertTrue(Commands.findCommand("event") instanceof EventCommand);
        assertTrue(Commands.findCommand("bye") instanceof ExitCommand);
        assertTrue(Commands.findCommand("list") instanceof ListCommand);
        assertTrue(Commands.findCommand("todo") instanceof ToDoCommand);
        assertTrue(Commands.findCommand("alias") instanceof AliasCommand);
        assertNull(Commands.findCommand("iwowqjdnoqwdnq"));
    }
}
