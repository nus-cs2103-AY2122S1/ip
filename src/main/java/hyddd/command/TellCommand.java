package hyddd.command;

import hyddd.task.TaskList;
import hyddd.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * TellCommand will handle the situation when a user wants to search a task by specific time.
 */
public class TellCommand extends Command {
    private final TaskList taskList;
    private final TextUi textUi;
    private final String time;

    /**
     * Constructor for TellCommand class.
     *
     * @param taskList TaskList in hyddd.
     * @param textUi hyddd's UI.
     * @param time String value for the time users take in.
     */
    public TellCommand(TaskList taskList, TextUi textUi, String time) {
        this.taskList = taskList;
        this.textUi = textUi;
        this.time = time;
    }

    /**
     * Returns hyddd's response when user wants to search tasks by time that user takes in.
     *
     * @return hyddd's response.
     */
    @Override
    public String returnResponse() {
        return tell();
    }

    private String tell() {
        return textUi.getSpecificDateEventUi() + taskList.getSpecificDateEvent(time);
    }
}
