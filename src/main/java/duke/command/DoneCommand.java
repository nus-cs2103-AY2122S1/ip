package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand() {
        setCommandString("done");
    }

    /**
     * Parses the user input for a taskNumber.
     * Then marks the specified task as done.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response telling the user which task was marked as done.
     * @throws DukeException Any exception caught when executing this command.
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        assert input.substring(0, getCommandLength() - 1).equals(getCommandString())
                : "Input should start with command";
        assert taskList != null : "taskList should not be null";

        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input a task number.");
        }

        String data = input.substring(getCommandLength()).strip();

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number.");
        }

        return taskList.doTask(idx);
    }
}
