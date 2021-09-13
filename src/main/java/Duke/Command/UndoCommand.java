package duke.command;

import duke.task.TaskList;
import duke.uimanager.TextUi;

/**
 * @@author Hang Zelin
 *
 * UndoCommand will handle the situation when a user wants to Undo a most recent task.
 */
public class UndoCommand extends Command{
    private final TaskList taskList;
    private final TextUi textUi;

    /**
     * Constructor for UndoCommand class.
     *
     * @param taskList TaskList in Duke.
     * @param textUi Duke's UI.
     */
    public UndoCommand(TaskList taskList, TextUi textUi) {
        this.taskList = taskList;
        this.textUi = textUi;
    }

    /**
     * Return Duke's response when user wants to undo a most recent task.
     *
     * @return Duke's response.
     */
    @Override
    public String returnResponse() {
        return undoMessages();
    }

    private String undoMessages() {
        String text;
        text = textUi.undoUi() + taskList.undo();
        return text;
    }

}
