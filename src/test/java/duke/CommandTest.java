package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the methods in Command class.
 */
public class CommandTest {

    private Command command;

    CommandTest() {
        this.command = new Command("test.txt");
        this.command.setTaskListEmpty();
    }

    @Test
    public void runCommand(){
        CommandTest test = new CommandTest();
        assertEquals("Bye! See you next time!", test.command.getReplyFromDuke("bye", ""));
        assertEquals("Please enter a new task or action.", test.command.getReplyFromDuke("empty", ""));

    }
}
