package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command to delete a task, or delete tasks that satisfy a specified condition.
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {
        setCommandString("delete");
    }

    /**
     * Parses the user input for a taskNumber, or a condition.
     * Then deletes the specified task, or all tasks matching the condition.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response confirming deletion and telling the user the number of tasks left.
     * @throws DukeException Any exception caught when executing this command.
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input a task number.");
        }

        String data = input.substring(getCommandLength()).strip();

        if (data.equals("done")) {
            return taskList.deleteDone();
        }

        if (data.equals("expired")) {
            return taskList.deleteExpired();
        }

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number");
        }

        return taskList.deleteTask(idx);
    }
}
