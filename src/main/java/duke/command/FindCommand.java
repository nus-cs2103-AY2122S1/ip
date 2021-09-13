package duke.command;

import duke.Duke;
import duke.GUI;
import duke.task.TaskList;

/**
 * Represents the general find command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for the Find Command.
     * @param duke Duke chatbot that is in use.
     * @param keyword keyword to find.
     */
    public FindCommand(Duke duke, String keyword) {
        super(duke);
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public String execute(TaskList taskList) {
        TaskList tasksFound = this.duke.findTasks(this.keyword);
        return GUI.sendFindMessage(tasksFound);
    }
}
