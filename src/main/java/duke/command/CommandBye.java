package duke.command;

import duke.DukeStorage;
import duke.DukeUi;
import duke.exception.DukeCommandException;
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
            isEnd = false;
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return isEnd;
    }

    /**
     * Parses the user input into the right format for the command
     *
     * @param userArgs Arguments to the command as provided by the user.
     */
    public static DukeCommand parseCommand(String[] userArgs) throws DukeCommandException {
        assert userArgs != null;
        assert userArgs.length != 0;
        assert userArgs[0].equals("bye");
        if (userArgs.length > 1) {
            throw new DukeCommandException("bye");
        }

        return new CommandBye();
    }
}
