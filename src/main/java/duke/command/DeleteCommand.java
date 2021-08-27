package duke.command;

import duke.Duke;
import duke.exception.DukeException;

public class DeleteCommand extends Command {
    public DeleteCommand() {
        setCommandString("delete");
    }

    /**
     * Parses the user input for a taskNumber, or a condition.
     * Then deletes the specified task, or all tasks matching the condition
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

        if (data.equals("done")) {
            Duke.getTaskList().deleteDone();
            return;
        }

        if (data.equals("expired")) {
            Duke.getTaskList().deleteExpired();
            return;
        }

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number");
        }

        Duke.getTaskList().deleteTask(idx);
    }
}
