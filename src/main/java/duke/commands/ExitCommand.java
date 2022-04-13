package duke.commands;

import java.io.IOException;

import duke.TaskList;

/**
 * Command that exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructor for ExitCommand.
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
