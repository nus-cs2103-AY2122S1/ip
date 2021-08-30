package duketest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Command;

public class CommandTest {
    @Test
    public void commandByeTest() {
        assertEquals(Command.BYE, Command.readInput("bye")); }

    @Test
    public void commandDeadlineTest() {
        assertEquals(Command.DEADLINE, Command.readInput("deadline"));
    }

    @Test
    public void commandDoneTest() {
        assertEquals(Command.DONE, Command.readInput("done")); }

    @Test
    public void commandEventTest() {
        assertEquals(Command.EVENT, Command.readInput("event"));
    }

    @Test
    public void commandListTest() {
        assertEquals(Command.LIST, Command.readInput("list"));
    }

    @Test
    public void commandTodoTest() {
        assertEquals(Command.TODO, Command.readInput("todo"));
    }

}
