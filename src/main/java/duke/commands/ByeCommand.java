package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

/**
 * Represents a Bye Command which includes information about
 * how it should be executed
 */

public class ByeCommand extends Command {

    /**
     * Constructor of a Bye Command
     */

    public ByeCommand() {
    }

    /**
     * Check if Command is Bye Command which would exit the program when executed
     *
     * @return Boolean of if command is Bye Command
     */

    @Override
    public boolean isExit() {
        return true;
    }


    /**
     * Executes the Bye Command by ending the session
     *
     * @param tasklist Tasklist containing list of Tasks
     * @param ui Ui that handles what is shown to the user
     * @param storage Storage that handles writing and reading save file
     */

    @Override
    public String execute(Tasklist tasklist, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }

}
