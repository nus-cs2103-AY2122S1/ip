package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void addCommandTest_todo_sameResult() {
        assertEquals("todo go home",
                new AddCommand("todo", "go home").toString());
    }

    @Test
    public void addCommandTest_deadline_sameResult() {
        assertEquals("deadline go home /by 2021-08-21 2359",
                new AddCommand("deadline", "go home /by 2021-08-21 2359").toString());
    }

    @Test
    public void addCommandTest_event_sameResult() {
        assertEquals("event go home /at 2021-08-21 2359",
                new AddCommand("event", "go home /at 2021-08-21 2359").toString());
    }

    @Test
    public void deleteCommandTest_sameResult() {
        assertEquals("delete 1", new DeleteCommand(0).toString());
    }

    @Test
    public void doneCommandTest_sameResult() {
        assertEquals("done 1", new DoneCommand(0).toString());
    }

    @Test
    public void exitCommand_sameResult() {
        assertEquals("bye", new ExitCommand().toString());
    }

    @Test
    public void listCommand_sameResult() {
        assertEquals("list", new ListCommand().toString());
    }
}
