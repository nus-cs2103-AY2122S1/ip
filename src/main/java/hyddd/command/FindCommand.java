package hyddd.command;

import hyddd.task.TaskList;
import hyddd.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * FindCommand will handle the situation when a user wants to find a task using keywords.
 */
public class FindCommand extends Command {
    private final TaskList taskList;
    private final TextUi textUi;
    private final String keyword;

    /**
     * Constructor for FindCommand class.
     *
     * @param taskList TaskList in hyddd.
     * @param textUi hyddd's UI.
     * @param keyword String value for the keyword.
     */
    public FindCommand(TaskList taskList, TextUi textUi, String keyword) {
        this.taskList = taskList;
        this.textUi = textUi;
        this.keyword = keyword;
    }

    /**
     * Returns hyddd's response when user wants to find tasks by keyword.
     *
     * @return hyddd's response.
     */
    @Override
    public String returnResponse() {
        return find();
    }

    private String find() {
        return textUi.findTasksUi() + taskList.findTasks(keyword);
    }
}
