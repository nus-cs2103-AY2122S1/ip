package duke.command;

import duke.task.TaskList;
import duke.uimanager.TextUi;

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
     * @param taskList TaskList in Duke.
     * @param textUi Duke's UI.
     * @param keyword String value for the keyword.
     */
    public FindCommand(TaskList taskList, TextUi textUi, String keyword) {
        this.taskList = taskList;
        this.textUi = textUi;
        this.keyword = keyword;
    }

    /**
     * Returns Duke's response when user wants to find tasks by keyword.
     *
     * @return Duke's response.
     */
    @Override
    public String returnResponse() {
        return find();
    }

    private String find() {
        return textUi.findTasksUi() + taskList.findTasks(keyword);
    }
}
