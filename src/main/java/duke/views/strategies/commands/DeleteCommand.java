package duke.views.strategies.commands;

import duke.domain.Task;
import duke.domain.TaskList;
import duke.shared.DukeException;

/**
 * Encapsulates a command to remove a task object to the task list.
 */
public class DeleteCommand extends TaskCommand {
    private static Command singleInstance;

    public DeleteCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new DeleteCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) throws DukeException {
        assert query != null;
        try {
            int index = Integer.parseInt(query.substring(DELETE.length()).strip()) - 1;
            if (index >= userTasks.size() || index < 0) {
                throw DukeException.createIndexOutOfBoundsException(userTasks.size(), index + 1);
            }
            Task removedTask = userTasks.remove(index);
            return String.format("Noted. I've removed this task:%s%s%s%s%s", System.lineSeparator(), removedTask,
                    System.lineSeparator(), formatTaskCount(), System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.ExceptionCode.INCORRECT_ARGS, "Please give a number");
        }
    }
}
