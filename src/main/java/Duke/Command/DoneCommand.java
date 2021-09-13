package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * DoneCommand will handle the situation when a user wants to mark a task as done.
 */
public class DoneCommand extends Command{
    private final TaskList taskList;
    private final TextUi textUi;
    private final int index;

    /**
     * Constructor for DoneCommand class.
     *
     * @param taskList TaskList in Duke.
     * @param textUi Duke's UI.
     * @param index Index of the specific task.
     */
    public DoneCommand(TaskList taskList, TextUi textUi, int index){
        this.taskList = taskList;
        this.textUi = textUi;
        this.index = index;
    }

    /**
     * Returns Duke's response when user wants to mark a task as done.
     *
     * @return Duke's response.
     */
    @Override
    public String returnResponse() {
        return markDone(index);
    }

    private String markDone(int index) {
        String text;
        try {
            taskList.detectIndex(index);
            text = textUi.markDoneUi(taskList.get(index).getTaskStatus());
            taskList.markDone(index);
        } catch (DukeException e) {
            text = e.getErrorMessage();
        }
        return text;
    }
}
