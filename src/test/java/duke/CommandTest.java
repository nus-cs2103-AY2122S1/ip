package duke;

import org.junit.jupiter.api.Test;

import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

public class CommandTest {

    @Test
    public void testCommand() {
        TaskList taskList = new TaskList();

        System.out.println(new HelpCommand().execute());
        System.out.println(new EmptyCommand().execute());
        System.out.println(new ExitCommand().execute());
        System.out.println(new ListCommand(taskList).execute());
        System.out.println(new FindCommand(new String[] {"find", "nothing"} , taskList).execute());


    }
}
