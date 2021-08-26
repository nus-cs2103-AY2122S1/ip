package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    private Command command;

    CommandTest() {
        this.command = new Command("test.txt");
        this.command.setTaskListEmpty();
    }

    @Test
    public void runCommand(){
        CommandTest test = new CommandTest();
        assertEquals(false, test.command.runNextCommand("bye", ""));
        assertEquals(true, test.command.runNextCommand("empty", ""));

    }
}
