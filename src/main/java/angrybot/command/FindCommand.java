package angrybot.command;

import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates a Find commands that deals with finding related tasks given a keyword.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class FindCommand extends AngryBotCommand {
    private final String keyword;

    /**
     * Constructor for a Find Command.
     *
     * @param ui      The Ui handler that handles the printing of message with respect to the command.
     * @param storage The storage handler that handles saving or loading data to local directory.
     * @param list    The TaskList handler that handles operation related to task.
     * @param keyword The keyword to find for related task in the task list.
     */
    public FindCommand(Ui ui, Storage storage, TaskList list, String keyword) {
        super(ui, storage, list);
        this.keyword = keyword;
    }

    /**
     * Executes the Find command. The command tries to find tasks
     * that have descriptions related to the keyword, and add those
     * tasks to a list, then prints the list of related task.
     *
     * @return A message to be shown on the GUI that contains all the related task.s1
     */
    @Override
    public String execute() {
        return ui.showRelatedTasks(list.findRelatedTask(keyword));
    }
}
