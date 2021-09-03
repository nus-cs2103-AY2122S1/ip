package duke.views.cli.strategies;

import java.util.List;

import duke.constants.Constants;
import duke.domain.Deadline;
import duke.domain.Event;
import duke.domain.Task;
import duke.domain.TaskList;
import duke.domain.Todo;
import duke.shared.DukeException;
import duke.shared.DukeException.ExceptionCode;
import duke.views.cli.strategies.commands.AddDeadlineCommand;
import duke.views.cli.strategies.commands.AddEventCommand;
import duke.views.cli.strategies.commands.AddTodoCommand;
import duke.views.cli.strategies.commands.DeleteCommand;
import duke.views.cli.strategies.commands.FindCommand;
import duke.views.cli.strategies.commands.ListCommand;
import duke.views.cli.strategies.commands.MarkDoneCommand;
import duke.views.cli.strategies.commands.OccurringOnCommand;

/**
 * A responder that is able to handle tasks with CRUD functionality.
 */
public class MultiTypeDelegated extends RespondWithDelegated {

    private final String list = "list";
    private final String done = "done";
    private final String todo = "todo";
    private final String deadline = "deadline";
    private final String event = "event";
    private final String delete = "delete";
    private final String on = "on";
    private final String find = "find";

    private final TaskList userTasks;

    /**
     * Creates a responder that handles multiple types of tasks.
     */
    public MultiTypeDelegated() {
        userTasks = new TaskList();
        commands.put(list, ListCommand.getInstance(userTasks));
        commands.put(done, MarkDoneCommand.getInstance(userTasks));
        commands.put(todo, AddTodoCommand.getInstance(userTasks));
        commands.put(deadline, AddDeadlineCommand.getInstance(userTasks));
        commands.put(event, AddEventCommand.getInstance(userTasks));
        commands.put(delete, DeleteCommand.getInstance(userTasks));
        commands.put(on, OccurringOnCommand.getInstance(userTasks));
        commands.put(find, FindCommand.getInstance(userTasks));
    }

    @Override
    public Task rehydrateFromString(String s) {
        String[] fields = s.split(Constants.Storage.PERSISTENCE_SEPARATOR_REGEX, -1);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].strip();
        }
        String typeString = fields[0];
        Task task = null;
        switch (typeString) {
        case Todo.TYPE_STRING:
            task = Todo.generateFromString(fields);
            break;
        case Event.TYPE_STRING:
            task = Event.generateFromString(fields);
            break;
        case Deadline.TYPE_STRING:
            task = Deadline.generateFromString(fields);
            break;
        default:
            throw new DukeException(ExceptionCode.UNPROCESSABLE_ENTITY);
        }

        return task;
    }

    @Override
    public String persistToStore() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : userTasks) {
            List<String> fields = task.getStorageFields();
            String stringToStore = String.join(Constants.Storage.PERSISTENCE_SEPARATOR_PRETTY, fields);
            stringBuilder.append(stringToStore);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public void load(List<String> dataList) {
        for (String data : dataList) {
            this.userTasks.add(rehydrateFromString(data));
        }
    }

    @Override
    public String respond(String query) {
        assert query != null;
        try {
            String specialResponse = super.respond(query);
            if (specialResponse != null) {
                return specialResponse;
            }
            throw new DukeException(DukeException.ExceptionCode.UNPROCESSABLE_ENTITY);
        } catch (DukeException e) {
            return e.getMessage() + System.lineSeparator();
        }
    }
}
