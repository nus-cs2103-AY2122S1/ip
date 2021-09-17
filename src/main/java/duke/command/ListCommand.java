package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

/**
 * A command which aims to list the current tasks.
 */
public class ListCommand extends Command {
    public static final String HELP_MESSAGE = "Dude this damn easy. Just type list into the textbook and send";

    /**
     * Returns the help messsage for listing.
     *
     * @return help message for listing.
     */
    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    /**
     * Lists the current tasks.
     *
     * @param tasks current list of task.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, FileManager fileManager) {
        return ui.printList(tasks);
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "list command";
    }
}
