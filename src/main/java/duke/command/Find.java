package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.ui.UiInterface;

/**
 * Class that handles the Find command
 */
public class Find extends Command {

    private final String[] words;

    /**
     * Constructs a Find instance.
     *
     * @param words Array of strings; Index 2 is used as the keyword
     */
    public Find(String[] words) {
        this.words = words;
    }

    /**
     * Executes the Find command.
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) throws DukeException {
        if (this.words.length != 2) {
            throw new InvalidFormatException("`find ${keyword}`");
        } else {
            String keyword = this.words[1];
            ui.print(taskList.stringifyTasksForFind(keyword));
        }
    }

    /**
     * Returns if the command is an exit.
     *
     * @return boolean indicating if command is exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
