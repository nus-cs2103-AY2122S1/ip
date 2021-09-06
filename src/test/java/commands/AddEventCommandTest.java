package commands;

import org.junit.jupiter.api.Test;

import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddEventCommandTest {
    @Test
    public void wrongCommandWord() {
        String input = "deadline blah -aa blah";
        Command command = new AddEventCommand(input, new TaskList());
        assertFalse(command.execute());
    }

    @Test
    public void noNameProvided() {
        String input = "deadline -at blah";
        Command command = new AddEventCommand(input, new TaskList());
        assertFalse(command.execute());
    }

    @Test
    public void noDescriptionProvided() {
        String input = "deadline blah -at";
        Command command = new AddEventCommand(input, new TaskList());
        assertFalse(command.execute());
    }

    @Test
    public void invalidDateProvided() {
        String input = "deadline blah /at blah 32/5/2000";
        Command command = new AddEventCommand(input, new TaskList());
        assertFalse(command.execute());
    }
}
