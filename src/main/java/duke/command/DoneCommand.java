package duke.command;

import duke.Duke;
import duke.exception.DukeException;

public class DoneCommand extends Command {
    public DoneCommand() {
        setCommandString("done");
    }

    /**
     * Parses the user input for a taskNumber.
     * Then marks the specified task as done.
     *
     * @param input Full user input
     * @throws DukeException Any exception caught when executing this command
     */
    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input a task number.");
        }

        String data = input.substring(getCommandLength()).strip();

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number");
        }

        Duke.getTaskList().doTask(idx);
    }
}
