package duke.command;

import duke.exception.DukeException;
import duke.Duke;

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
        String data = input.substring(getCommandLength()).strip();

        if (data.equals("done")) {
            Duke.taskList.deleteDone();
            return;
        }

        if (data.equals("expired")) {
            Duke.taskList.deleteExpired();
            return;
        }

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number");
        }

        Duke.taskList.deleteTask(idx);
    }
}
