package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest {

    @Test
    void isCommand() {
        Commands.DEADLINE.isCommand("deadline");
        Commands.DELETE.isCommand("delete");
        Commands.DONE.isCommand("done");
        Commands.EVENT.isCommand("event");
        Commands.EXIT.isCommand("bye");
        Commands.LIST.isCommand("list");
        Commands.TODO.isCommand("todo");
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