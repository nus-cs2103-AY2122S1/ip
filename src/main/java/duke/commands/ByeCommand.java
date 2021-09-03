package duke.commands;

import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Represents command to end Duke */
public class ByeCommand extends Command {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public String execute(TaskList tasks, CliUi cliUi, Storage storage) {
        cliUi.printOut(BYE_MESSAGE);
        return BYE_MESSAGE;
    }

    /**
     * Checks if this command exits Duke.
     *
     * @return True since the program exits once this command is issued.
     */
    @Override
    public boolean hasExited() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("TO PRINT: %s", BYE_MESSAGE);
    }
}
