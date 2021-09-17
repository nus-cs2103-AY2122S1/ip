package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The FindCommand class encapsulates the execution of the find command from the user.
 */
public class FindCommand extends Command {

    /** The keyword to be searched for. */
    private String keyword;

    /**
     * Constructor to initialise a FindCommand.
     *
     * @param keyword The keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the response to the find command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     * @return The String to be printed in the Duke GUI.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.showFind(taskList, keyword);
    }
}
