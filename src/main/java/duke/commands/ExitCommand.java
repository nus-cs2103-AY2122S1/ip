package duke.commands;

import duke.TaskList;

import java.io.IOException;

/**
 * Command that exits the program.
 */
public class ExitCommand extends Command{
    /**
     * Constructor for ExitCommand.
     *
     */
    public ExitCommand() {
        super("");
    }

    @Override
    public String execute(TaskList tasks) throws IOException {
        System.exit(1);
        return "";
    }
}
