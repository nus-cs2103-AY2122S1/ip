package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * AddCommand will handle the situation when a user wants to add a task.
 */
public class AddCommand extends Command{
    private final TaskList taskList;
    private final TextUi textUi;
    private final String taskType;
    private final String taskInfo;
    private final String timeInfo;

    /**
     * Constructor for adding a command class.
     *
     * @param taskList TaskList in Duke.
     * @param textUi Duke's UI.
     * @param taskType TaskType user takes in.
     * @param taskInfo TaskInfo user takes in.
     * @param timeInfo TimeInfo user takes in.
     */
    public AddCommand(TaskList taskList, TextUi textUi, String taskType, String taskInfo, String timeInfo){
        this.taskList = taskList;
        this.textUi = textUi;
        this.taskType = taskType;
        this.taskInfo = taskInfo;
        this.timeInfo = timeInfo;
    }

    /**
     * Returns Duke's response when adding a task to list.
     *
     * @return Duke's response.
     */
    @Override
    public String returnResponse() {
        String text;
        text = add(taskType, taskInfo, timeInfo);
        return text;
    }

    private String add(String ... commands) {
        String text;
        try {
            taskList.add(commands[0], commands[1], commands[2]);
            text = textUi.addUi(taskList.get(taskList.size() - 1).getTaskStatus(), taskList.size());
        } catch (DukeException e) {
            text = e.getErrorMessage();
        }
        return text;
    }
}
