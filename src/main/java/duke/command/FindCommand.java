package duke.command;

import duke.Duke;
import duke.exception.DukeException;

public class FindCommand extends Command {
    public FindCommand() {
        setCommandString("find");
    }

    /**
     * Parses the user input for a search string,
     * then finds all tasks that contain the search string and lists them
     *
     * @param input Full user input
     * @throws DukeException Exception thrown when search string is empty
     */
    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input a search string.");
        }

        String data = input.substring(getCommandLength()).strip();

        if (data.equals("")) {
            throw new DukeException("Please input a search string.");
        }

        Duke.getTaskList().find(data);
    }
}
