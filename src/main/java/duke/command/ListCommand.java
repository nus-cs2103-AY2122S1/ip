package duke.command;

import duke.Duke;
import duke.Parser;
import duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Returns the command word for a list command.
     *
     * @return "list" representing a list command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Displays the user's list of tasks.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     */
    @Override
    public void run(Duke duke, Parser parser) {
        Ui.displayTasks(duke.getList());
    }
}
