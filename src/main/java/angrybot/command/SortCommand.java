package angrybot.command;

import angrybot.storage.Storage;
import angrybot.task.TaskList;
import angrybot.ui.Ui;

/**
 * Encapsulates a Sort command that deals sorting the task in the task list.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class SortCommand extends AngryBotCommand {
    private final boolean isReverse;

    /**
     * Constructor for a SortCommand.
     *
     * @param ui        The Ui handler that handles the printing of message with respect to the command.
     * @param storage   The storage handler that handles saving or loading data to local directory.
     * @param list      The TaskList handler that handles operation related to task.
     * @param isReverse The order of sorting.
     */
    public SortCommand(Ui ui, Storage storage, TaskList list, boolean isReverse) {
        super(ui, storage, list);
        this.isReverse = isReverse;
    }

    /**
     * Executes the sort command. Sorts the list according to the order that the user wants.
     *
     * @return A message to be displayed on the GUI to show the user the sorted list.
     */
    @Override
    public String execute() {
        list.sortTaskList(isReverse);
        return ui.sortListMessage(list.getList());
    }
}
