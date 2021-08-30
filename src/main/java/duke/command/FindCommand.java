package duke.command;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class abstracts the find command that the user wants to execute.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Constructs a FindCommand that will delete the given task number when executed.
     *
     * @param keyword The String to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the command to find a given keyword in tasks.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFilteredList(tasks, keyword);
    }
}
