package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand() {
        setMainCommand("done");
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
        assert taskList != null : "taskList should not be null";

        int firstSpace = input.indexOf(' ');

        // No space after the command
        if (firstSpace == -1) {
            throw new DukeException("Please input a task number.");
        }

        String data = input.substring(firstSpace).strip();

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number.");
        }

        return taskList.doTask(idx);
    }
}
