package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a Find commands that deals with finding related tasks given a keyword.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Find extends DukeCommand {
    private final String keyword;

    /**
     * Constructor for a Find Command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     * @param keyword The keyword to find for related task in the task list.
     */
    public Find(Ui ui, Storage storage, TaskList list, String keyword) {
        super(ui, storage, list);
        this.keyword = keyword;
    }

    /**
     * Executes the Find command. The command tries to find tasks
     * that have descriptions related to the keyword, and add those
     * tasks to a list, then prints the list of related task.
     */
    @Override
    public void execute() {
        ui.printRelatedTasks(list.findRelatedTask(keyword));
    }
}
