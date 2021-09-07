package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents user command to find a task with a specific string in the TaskList.
 */
public class FindCommand extends Command {
    private String filter;

    /**
     * Class Constructor.
     * @param input The user input to indicate the string to search for.
     * @throws DukeException If the user did not input a description.
     */
    public FindCommand(String input) throws DukeException {
        try {
            this.filter = input.substring(5);
            assert(this.filter.length() > 0);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The description cannot be empty!");
        }
    }

    /**
     * Executes the find command. Tasks that contain the given string will be outputted and
     * shown to the user.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public String runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showSearchResult(filter, taskList);
    }

    /**
     * Indicates if the command ends the program after executing.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }


}
