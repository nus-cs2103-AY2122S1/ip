package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles finding keyword in task description of task in TaskList.
 */
public class CommandFind extends Command {
    /**
     * The list that tasks with keyword are added to
     */
    private final ArrayList<Task> relatedList;
    /**
     * The keyword used to find tasks
     */
    private final String keyword;

    /**
     * Constructor for the FindCommand.
     *
     * @param keyword The keyword used to find tasks.
     */
    public CommandFind(String keyword) {
        this.relatedList = new ArrayList<>();
        this.keyword = keyword;
    }

    /**
     * Finds tasks with keyword and add them into a list.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.find(relatedList, keyword);
        return ui.displayFindUi(relatedList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof CommandFind) {
            return this.keyword.equals(((CommandFind) obj).keyword);
        }

        return false;
    }
}
