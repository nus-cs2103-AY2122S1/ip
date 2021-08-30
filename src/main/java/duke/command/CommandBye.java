package duke.command;

import duke.DukeStorage;
import duke.DukeUi;
import duke.exception.DukeFileException;
import duke.task.TaskList;

/**
 * Represents a command to exit the app.
 */
public class CommandBye extends DukeCommand {
    private boolean isEnd = true;

    /**
     * Saves the task list and attempts to exit the app.
     * Does not exit if the task list failed to save.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        String response;
        try {
            DukeStorage.saveTaskList(tl);
            response = DukeUi.getGoodBye();
        } catch (DukeFileException e) {
            response = e.getMessage();
            this.isEnd = false;
        }
        return response;
    }

    /**
     * As described in DukeCommand.
     *
     * @return True if task list saved successfully, false otherwise.
     */
    @Override
    public boolean isExit() {
        return isEnd;
    }
}
