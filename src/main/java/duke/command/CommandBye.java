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
    public void execute(TaskList tl) {
        try {
            DukeStorage.saveTaskList(tl);
            DukeUi.sayBye();
        } catch (DukeFileException e) {
            DukeUi.printLine(e.getMessage());
            this.isEnd = false;
        }
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
