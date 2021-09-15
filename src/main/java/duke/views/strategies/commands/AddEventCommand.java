package duke.views.strategies.commands;

import duke.domain.Event;
import duke.domain.Task;
import duke.domain.TaskList;
import duke.shared.DukeException;

/**
 * Encapsulates a command to add an event object to the task list.
 */
public class AddEventCommand extends TaskCommand {
    private static Command singleInstance;

    public AddEventCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new AddEventCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) throws DukeException {
        assert query != null;
        String[] queries = query.substring(EVENT.length()).split("/at");
        if (queries.length != 2) {
            throw DukeException.createArgumentCountException(2, queries.length);
        }
        Task task = new Event(queries[0].strip(), queries[1].strip());
        boolean isUnique = userTasks.add(task);
        if (!isUnique) {
            return getDuplicateMessage();
        }
        return formatAdd(task);
    }
}
