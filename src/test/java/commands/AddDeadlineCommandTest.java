package commands;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeadlineCommandTest {

    @Test
    public void wrongCommandWord() {
        String input = "deadline blah -bb blah";
        Command command = new AddDeadlineCommand(input, new TaskList());
        assertEquals(Command.CommandReturnStatus.UNSUCCESSFUL, command.execute());
    }

    @Test
    public void noNameProvided() {
        String input = "deadline -by blah";
        Command command = new AddDeadlineCommand(input, new TaskList());
        assertEquals(Command.CommandReturnStatus.UNSUCCESSFUL, command.execute());
    }

    @Test
    public void noDescriptionProvided() {
        String input = "deadline blah -by";
        Command command = new AddDeadlineCommand(input, new TaskList());
        assertEquals(Command.CommandReturnStatus.UNSUCCESSFUL, command.execute());
    }

    @Test
    public void invalidDateProvided() {
        String input = "deadline blah /by blah 32/5/2000";
        Command command = new AddDeadlineCommand(input, new TaskList());
        assertEquals(Command.CommandReturnStatus.UNSUCCESSFUL, command.execute());
    }

}
