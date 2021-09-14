package duke.views.strategies;

import java.util.List;

import duke.constants.Constants;
import duke.domain.Deadline;
import duke.domain.Event;
import duke.domain.Task;
import duke.domain.TaskList;
import duke.domain.Todo;
import duke.shared.DukeException;
import duke.shared.DukeException.ExceptionCode;
import duke.views.Response;
import duke.views.strategies.commands.AddDeadlineCommand;
import duke.views.strategies.commands.AddEventCommand;
import duke.views.strategies.commands.AddTodoCommand;
import duke.views.strategies.commands.Command;
import duke.views.strategies.commands.DeleteCommand;
import duke.views.strategies.commands.FindCommand;
import duke.views.strategies.commands.ListCommand;
import duke.views.strategies.commands.MarkDoneCommand;
import duke.views.strategies.commands.OccurringOnCommand;

/**
 * A responder that is able to handle tasks with CRUD functionality.
 */
public class Parser extends RespondWith {

    private final TaskList userTasks;

    /**
     * Creates a responder that handles multiple types of tasks.
     */
    public Parser() {
        userTasks = new TaskList();
        commands.put(Command.LIST, ListCommand.getInstance(userTasks));
        commands.put(Command.DONE, MarkDoneCommand.getInstance(userTasks));
        commands.put(Command.TODO, AddTodoCommand.getInstance(userTasks));
        commands.put(Command.DEADLINE, AddDeadlineCommand.getInstance(userTasks));
        commands.put(Command.EVENT, AddEventCommand.getInstance(userTasks));
        commands.put(Command.DELETE, DeleteCommand.getInstance(userTasks));
        commands.put(Command.ON, OccurringOnCommand.getInstance(userTasks));
        commands.put(Command.FIND, FindCommand.getInstance(userTasks));
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

    @Override
    public Response respondWithMetadata(String query) {
        assert query != null;
        try {
            String specialResponse = super.respond(query);
            if (specialResponse != null) {
                return new Response(specialResponse, Response.ResponseType.INFO);
            }
            throw new DukeException(DukeException.ExceptionCode.UNPROCESSABLE_ENTITY);
        } catch (DukeException e) {
            return new Response(e.getMessage() + System.lineSeparator(), Response.ResponseType.ERROR);
        }
    }
}
