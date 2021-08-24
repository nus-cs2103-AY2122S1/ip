package duke.command;

import duke.Duke;
import duke.exception.DukeException;

public class FindCommand extends Command {
    public FindCommand() {
        setCommandString("find");
    }

    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input a search string.");
        }

        String data = input.substring(getCommandLength()).strip();

        if (data.equals("")) {
            throw new DukeException("Please input a search string.");
        }

        Duke.taskList.find(data);
    }
}