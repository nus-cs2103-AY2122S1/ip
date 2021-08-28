package duke.views.cli.strategies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.constants.Constants;
import duke.domain.Deadline;
import duke.domain.Event;
import duke.domain.Task;
import duke.domain.Todo;
import duke.shared.DukeException;
import duke.shared.DukeException.ExceptionCode;

/**
 * A responder that is able to handle tasks with CRUD functionality.
 */
public class MultiType extends RespondWith {

    private final String list = "list";
    private final String done = "done";
    private final String todo = "todo";
    private final String deadline = "deadline";
    private final String event = "event";
    private final String delete = "delete";
    private final String on = "on";
    private final String find = "find";

    private final List<Task> userTasks;

    /**
     * Creates a responder that handles multiple types of tasks.
     */
    public MultiType() {
        userTasks = new ArrayList<>();
        commands.put(list, this::listString);
        commands.put(done, this::markDone);
        commands.put(todo, this::addTodo);
        commands.put(deadline, this::addDeadline);
        commands.put(event, this::addEvent);
        commands.put(delete, this::deleteTask);
        commands.put(on, this::occurringOn);
        commands.put(find, this::findTask);
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
        case Todo.TYPE_STRING: {
            task = Todo.generateFromString(fields);
            break;
        }
        case Event.TYPE_STRING: {
            task = Event.generateFromString(fields);
            break;
        }

        case Deadline.TYPE_STRING: {
            task = Deadline.generateFromString(fields);
            break;
        }
        default: {
            throw new DukeException(ExceptionCode.UNPROCESSABLE_ENTITY);
        }
        }

        return task;
    }

    @Override
    public String persistToStore() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : userTasks) {
            List<String> fields = task.storageFields();
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

    private String stringifyTasks(List<Task> tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += String.format("%d. %s%s", (i + 1), tasks.get(i), System.lineSeparator());
        }
        return result;
    }

    private String listTasksWithMessage(String message, List<Task> tasks) {
        return listTasksWithMessage(message, tasks, "Nothing found");
    }

    private String listTasksWithMessage(String message, List<Task> tasks, String emptyMessage) {
        if (tasks.size() == 0) {
            return emptyMessage + System.lineSeparator();
        }
        String result = message + System.lineSeparator();

        result += stringifyTasks(tasks);
        return result;
    }

    private String listString(String _query) {
        return listTasksWithMessage("Here are the tasks in your list:",
                userTasks, "You're clear (for now)");

    }

    private String markDone(String query) throws DukeException {
        try {
            int index = Integer.parseInt(query.substring(done.length()).strip()) - 1;
            if (index >= userTasks.size() || index < 0) {
                throw DukeException.createIndexOutOfBoundsException(userTasks.size(), index + 1);
            }
            Task task = userTasks.get(index);
            task.finish();
            return String.format("Nice! I've marked this task as done:%s\t%s%s", System.lineSeparator(), task,
                    System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.ExceptionCode.INCORRECT_ARGS, "Please give a number");
        }
    }

    private String formatTaskCount() {
        String s = "";
        if (userTasks.size() != 1) {
            s = "s";
        }
        return String.format("Now you have %d task%s in the list.", userTasks.size(), s);
    }

    private String formatAdd(Task t) {
        return String.format("Got it. I've added this task:%s%s%s%s%s", System.lineSeparator(), t,
                System.lineSeparator(), formatTaskCount(), System.lineSeparator());
    }

    private String addTodo(String query) {
        Task task = new Todo(query.substring(todo.length()).strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String addDeadline(String query) throws DukeException {
        String[] queries = query.substring(deadline.length()).split("/by");
        if (queries.length != 2) {
            throw DukeException.createArgumentCountException(2, queries.length);
        }
        Task task = new Deadline(queries[0].strip(), queries[1].strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String addEvent(String query) throws DukeException {
        String[] queries = query.substring(event.length()).split("/at");
        if (queries.length != 2) {
            throw DukeException.createArgumentCountException(2, queries.length);
        }
        Task task = new Event(queries[0].strip(), queries[1].strip());
        userTasks.add(task);
        return formatAdd(task);
    }

    private String deleteTask(String query) throws DukeException {
        try {
            int index = Integer.parseInt(query.substring(delete.length()).strip()) - 1;
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

    private String occurringOn(String query) throws DukeException {
        String dateQuery = query.substring(on.length()).strip();
        if (dateQuery.length() == 0) {
            dateQuery = Constants.Input.DATETIME_FORMATTER.format(LocalDateTime.now());
        }

        List<Task> relevantTasks = new ArrayList<>();
        for (Task task : userTasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isDueOn(dateQuery)) {
                    relevantTasks.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isOccurringOnDay(dateQuery)) {
                    relevantTasks.add(event);
                }
            }
        }

        return listTasksWithMessage("Tasks occurring on that day:",
                relevantTasks, "No tasks on that day");
    }

    private String findTask(String query) {
        String keyword = query.substring(find.length()).strip();
        List<Task> filteredTasks = userTasks.stream()
                .filter(task -> task.match(keyword))
                .collect(Collectors.toList());
        return listTasksWithMessage("Here are the matching tasks in your list:",
                filteredTasks, "No matches found");
    }

    @Override
    public String respond(String query) {
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
