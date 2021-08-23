package duke.command;

import duke.Duke;
import duke.exception.DukeException;

public class DoneCommand extends Command {
    public DoneCommand() {
        setCommandString("done");
    }

    @Override
    public void parse(String input) throws DukeException {
        String data = input.substring(getCommandLength()).strip();

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number");
        }

        Duke.taskList.doTask(idx);
    }
}
