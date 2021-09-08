package duke.views.strategies.commands;

import duke.domain.Deadline;
import duke.domain.Task;
import duke.domain.TaskList;
import duke.shared.DukeException;

/**
 * Encapsulates a command to add a deadline object to the task list.
 */
public class AddDeadlineCommand extends TaskCommand {
    private static Command singleInstance;

    public AddDeadlineCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new AddDeadlineCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) throws DukeException {
        assert query != null;
        String[] queries = query.substring(DEADLINE.length()).split("/by");
        if (queries.length != 2) {
            throw DukeException.createArgumentCountException(2, queries.length);
        }
        Task task = new Deadline(queries[0].strip(), queries[1].strip());
        boolean isUnique = userTasks.add(task);
        if (!isUnique) {
            return getDuplicateMessage();
        }
        return formatAdd(task);
    }
}
