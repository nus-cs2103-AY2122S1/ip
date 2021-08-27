package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommandsTest {

    @Test
    void isCommand() {
        assertTrue(Commands.DEADLINE.isCommand("deadline"));
        assertTrue(Commands.DELETE.isCommand("delete"));
        assertTrue(Commands.DONE.isCommand("done"));
        assertTrue(Commands.EVENT.isCommand("event"));
        assertTrue(Commands.EXIT.isCommand("bye"));
        assertTrue(Commands.LIST.isCommand("list"));
        assertTrue(Commands.TODO.isCommand("todo"));
    }

    @Test
    void getCommand() {
        assertTrue(Commands.DEADLINE.getCommand() instanceof DeadlineCommand);
        assertTrue(Commands.DELETE.getCommand() instanceof DeleteCommand);
        assertTrue(Commands.DONE.getCommand() instanceof DoneCommand);
        assertTrue(Commands.EVENT.getCommand() instanceof EventCommand);
        assertTrue(Commands.EXIT.getCommand() instanceof ExitCommand);
        assertTrue(Commands.LIST.getCommand() instanceof ListCommand);
        assertTrue(Commands.TODO.getCommand() instanceof ToDoCommand);

    }
}